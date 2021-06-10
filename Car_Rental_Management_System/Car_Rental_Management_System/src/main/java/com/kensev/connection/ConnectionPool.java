package com.kensev.connection;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ConnectionPool {
	//private static int CURRENT_CONNECTIONS = 5;
	private final static int MINIMUM_CONNECTIONS = 5;
	// private static LocalTime prevTime = LocalTime.now();
	// private static int totalQueriesToDB = 0;

	//private static String jdbcURL = "jdbc:mysql://localhost:3306/car_rental_management_system";
	private static String jdbcURL = "jdbc:mysql://localhost/car_rental_management_system?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static String jdbcUsername = "root";
	private static String jdbcPassword = "root";
	private static String DRIVER = "com.mysql.cj.jdbc.Driver";

	private static Stack<Connection> availableConnections = new Stack<>();
	private static List<Connection> usedConnections = new ArrayList<>(MINIMUM_CONNECTIONS);
	
	static String lck = "";
	static {
		synchronized (lck) {
			try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e) {
				throw new Error("FATAL ....");
			}

			for (int count = 0; count < MINIMUM_CONNECTIONS; count++) {
				try {
					availableConnections.push(createConnection());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			Thread refreshPool = new Thread(new Runnable() {
				@Override
				synchronized public void run() {
					while (true) {
						try {
							Thread.sleep(5000);
							checkAndFixPool();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
			refreshPool.start();
		}
	}

	synchronized private static void checkAndFixPool() {

		for (Connection conn : availableConnections) {
			try (Statement statement = conn.createStatement();) {
				statement.executeQuery("SELECT 1");
			} catch (SQLException e) {
				availableConnections.remove(conn);
				try {
					availableConnections.push(createConnection());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}

	}

	private static Connection createConnection() throws SQLException {
		return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
	}

	synchronized static public Connection getConnection() {

		while (availableConnections.isEmpty()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		Connection newConnection = availableConnections.pop();
		usedConnections.add(newConnection);

		Handler handler = new Handler(newConnection);

		// updateUsedConnectionsStatistics();

		return (Connection) Proxy.newProxyInstance(Connection.class.getClassLoader(), new Class[] { Connection.class },
				handler);
	}
	/*
	 * private static void updateUsedConnectionsStatistics() { long periodInSeconds
	 * = Duration.between(prevTime, LocalTime.now()).getSeconds(); prevTime =
	 * LocalTime.now();
	 * 
	 * totalQueriesToDB++; long queriesPerSecond = totalQueriesToDB /
	 * periodInSeconds;
	 * 
	 * }
	 */

	synchronized public static void releaseConnection(Connection conn) {
		if (conn == null) {
			return;
		}

		try {
			if (!conn.getAutoCommit()) {
				conn.rollback();
				conn.setAutoCommit(true);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		usedConnections.remove(conn);
		availableConnections.push(conn);
	}

	synchronized public static void shutdown() throws SQLException {
		for (Connection conn : usedConnections) {
			releaseConnection(conn);
		}
		for (Connection conn : availableConnections) {
			conn.close();
		}
	}

}
