package com.kensev.entitites;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.kensev.connection.ConnectionPool;

public class Account {

	private String username;
	private String email;
	private String password;
	
	public boolean login(String email, String password) throws SQLException {
		String queryStatement = String.format("SELECT username, email, password FROM accounts WHERE email = '%s' AND password = '%s'", email, password);
		
		try(Connection connection = ConnectionPool.getConnection();
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(queryStatement);){
			
			if(rs.next()) {
				this.setUsername(rs.getString("username"));
				this.setEmail(email);
				this.setPassword(password);
				return true;
			}
			else return false;
		}
	}

	public boolean register(String username, String email, String password) throws SQLException {
		if(accountWithEmailExists(email)) {
			return false;
		}
		
		String insertStatement = String.format("INSERT INTO accounts(username, email, password) VALUES('%s','%s','%s')",username, email, password);
		try (Connection connection = ConnectionPool.getConnection();
				Statement statement = connection.createStatement()){
			statement.executeUpdate(insertStatement);
		}
		this.setUsername(username);
		this.setEmail(email);
		this.setPassword(password);
		
		return true;
	}
	
	private boolean accountWithEmailExists(String email) throws SQLException {
		
		try(Connection connection = ConnectionPool.getConnection();
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery("SELECT * FROM accounts WHERE email = '" + email +"'");){
			if(rs.next()) {
				return true;
			}
			return false;
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
