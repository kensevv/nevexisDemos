package com.frantishex.persistent.base;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Stack;

import org.apache.log4j.Logger;

import com.frantishex.persistent.base.exception.ObjectNotFoundException;

import fes.resources.Messages;

public class ConnectionManager {

	private static Logger logger = Logger.getLogger(ConnectionManager.class);

	private static ThreadLocal<Connection> tpCnn = new ThreadLocal<Connection>();

	private static Stack<Connection> pool = new Stack<Connection>();

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new Error("Fatal", e);
		}
	}

	private static boolean isProduction() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress().startsWith("172");
	}

	private static String getConnectionString() throws UnknownHostException {
		return isProduction() ? Messages.getString("prod.ConnectionManager.connection.string")
				: Messages.getString("ConnectionManager.connection.string");
	}

	private static synchronized Connection createConnection() throws SQLException, UnknownHostException {
		//Connection cnn = pool.isEmpty() ? DriverManager.getConnection(getConnectionString()) : pool.pop();
		
		Connection cnn;
		if(pool.empty()) {
			cnn = DriverManager.getConnection(getConnectionString());
		}
		else {
			cnn = pool.pop();
			// Check if the connection from pool is still valid -> replace it with new if it's not
			checkAndFixConnection(cnn);
		}
		
		cnn.setReadOnly(false);
		cnn.setAutoCommit(true);
		cnn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
		return cnn;
	}

	private static void checkAndFixConnection(Connection cnn) throws UnknownHostException, SQLException {
		try (Statement statement = cnn.createStatement();){
			statement.executeQuery("SELECT 1");
		} catch (SQLException e) {
			// pitay bate vanko ? cnn.close();
			cnn = DriverManager.getConnection(getConnectionString());
		}
	}

	public static Connection getConnection() {
		Connection connection = tpCnn.get();
		if (null == connection) {
			try {
				setCnn();
				connection = getConnection();
			} catch (UnknownHostException | SQLException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

	// FILTER FILTER FILTER FILTER FILTER FILTER
	/**
	 * Call from Filter to set connection per thread
	 * 
	 * @throws UnknownHostException
	 * @throws SQLException
	 */
	public static void setCnn() throws UnknownHostException, SQLException {
		tpCnn.set(createConnection());
	}

	public static void releaseConnection() {
		Connection cnn = tpCnn.get();
		if (null == cnn) {
			return;
		}
		try {
			if (!cnn.getAutoCommit()) {
				cnn.rollback();
			}
		} catch (SQLException e) {
		}
		pool.push(cnn);
		tpCnn.set(null);
	}

	public static void destroyPool() {
		pool.forEach((cnn) -> {
			try {
				cnn.close();
			} catch (SQLException e) {
			}
		});
	}

	/**
	 * Execute SQL statement and callback the passed ISQLHelper Use this method when
	 * a specific query for a single object is constructed instance is to process
	 * the result set.
	 * 
	 */
	public static void executeAndProcessQuery(ISQLHelper isqlh, String sSQL)
			throws SQLException, ObjectNotFoundException {
		Connection connection = getConnection();
		try (Statement stmt = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
				ResultSet rst = stmt.executeQuery(sSQL)) {
			try {
				isqlh.processResultSet(rst);

				if (!connection.getAutoCommit()) {
					connection.commit();
				}

			} catch (SQLException | ObjectNotFoundException e) {
				if (!connection.getAutoCommit()) {
					connection.rollback();
				}
				throw e;
			}
		}

	}

	public static void executeAndProcessQueryRO(ISQLHelper isqlh, String sSQL)
			throws SQLException, ObjectNotFoundException {

		Connection cnn = getConnection();
		cnn.setReadOnly(true);

		try (Statement stmt = cnn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
				ResultSet rst = stmt.executeQuery(sSQL)) {
			try {
				isqlh.processResultSet(rst);

				if (!cnn.getAutoCommit()) {
					cnn.commit();
				}
			} catch (SQLException | ObjectNotFoundException e) {
				if (!cnn.getAutoCommit()) {
					cnn.rollback();
				}
				throw e;
			}
		}
	}

	static void executeAndProcessQuery(ISQLHelper isqlh, String sSQL, Connection cnn)
			throws SQLException, ObjectNotFoundException {
		try (Statement stmt = cnn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
				ResultSet rst = stmt.executeQuery(sSQL)) {
			isqlh.processResultSet(rst);
		}
	}

	public static void executeAndProcessQuery(ISQLHelper isqlh, PreparedStatement stmt)
			throws SQLException, ObjectNotFoundException {
		try (ResultSet rst = stmt.executeQuery()) {
			isqlh.processResultSet(rst);
		}
	}

	public static void processDatabaseWork(ISQLConnectionHelper isqlch) throws SQLException {
		Connection cnn = getConnection();

		try {
			isqlch.process(cnn);
		} catch (SQLException e) {
			if (!cnn.getAutoCommit()) {
				cnn.rollback();
			}
			logger.error("Error", e);
			throw e;
		}

	} // processDatabaseWork

	/**
	 * Use this method explicitly when working with transaction because it issues
	 * autocommit=false
	 * 
	 */
	public static void processDatabaseWork2(ISQLConnectionHelper2 isqlch) throws Exception {

		Connection cnn = getConnection();

		cnn.setAutoCommit(false);

		isqlch.process(cnn);

		cnn.commit();

	} // processDatabaseWork

	public synchronized static PreparedStatement prepareStatement(String sql) throws SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_UPDATABLE);
		pstmt.setMaxRows(10000);
		return pstmt;
	}

	public synchronized static PreparedStatement prepareStatementReadOnly(String sql) throws SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_READ_ONLY);
		pstmt.setMaxRows(1000);
		return pstmt;
	}

	public static void unPrepareStatement(Statement stmt) {
		if (null != stmt) {
			try {
				stmt.close();
			} catch (SQLException e) {
			}
		}
	}

	public static void execute(String sSQL) throws SQLException {
		try (PreparedStatement stmt = getConnection().prepareStatement(sSQL)) {
			stmt.execute();
		}
	}
}
