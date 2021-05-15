package com.sqlProcedures;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProceduresTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");

		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/procedurestest?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
				CallableStatement stmt = con.prepareCall("{call getYoungerAndOlderThan(?)}");) {
			stmt.setInt(1, 10);
			stmt.execute();

			do {
				try (ResultSet rs = stmt.getResultSet()) {
					System.out.println("Next resultSet . . .");
					while (rs.next()) {
						System.out.println(String.format("%d \t %s \t %d", rs.getInt("id"), rs.getString("Name"),
								rs.getInt("Age")));
					}
				}
			} while (stmt.getMoreResults());
		}
	}
}