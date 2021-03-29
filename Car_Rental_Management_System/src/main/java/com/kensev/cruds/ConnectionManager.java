package com.kensev.cruds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private static String jdbcURL = "jdbc:mysql://localhost:3306/car_rental_management_system";
	private static String jdbcUsername = "root";
	private static String jdbcPassword = "root";

	private static Connection jdbcConnection;

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (ClassNotFoundException | SQLException exception) {
			throw new Error("Fatal ......");
		}
	}

	public static Connection getConnection() throws SQLException {
		return jdbcConnection;
	}
}