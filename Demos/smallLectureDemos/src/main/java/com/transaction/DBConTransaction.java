package com.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBConTransaction {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_rental", "root", "root");
		con.setAutoCommit(false);
		Statement stmt = con.createStatement();
		
		List<String> updates = new ArrayList<String>();
		
		String[] strs = { "INSERT INTO person(name) VALUES('transaction1');",
				"INSERT INTO person(name) VALUES('transaction2');",
				"INSERT INTO person(name) VALUES('transaction3');",
				"INSERT INTO person(name) VALUES('transaction4');" };
		for(int i =  0; i < strs.length; i++){
		     updates.add(strs[i]);
		     
		}
		try {				
			for (String updateQuery : updates) {
				System.out.println("Ho");
				stmt.executeUpdate(updateQuery);
			}
			
			con.commit();
			System.out.println("Commited!");
			
		} catch (Exception e) {
			con.rollback();
			System.out.println("exception, transaction rolledback!");
		}
	}
}