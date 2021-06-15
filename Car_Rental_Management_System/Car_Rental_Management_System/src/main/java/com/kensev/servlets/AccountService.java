package com.kensev.servlets;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.kensev.connection.ConnectionPool;
import com.kensev.entitites.Account;
import com.kensev.entitites.Roles;
import com.kensev.security.PasswordService;

public class AccountService {
	private final PasswordService passwordService = new PasswordService();

	public Account login(String email, String password) throws SQLException, NoSuchAlgorithmException {
		String queryStatement = String.format("SELECT * FROM accounts WHERE email = '%s'", email);

		try (Connection connection = ConnectionPool.getConnection();
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(queryStatement);) {

			if (rs.next()) {
				if (passwordService.comparePasswords(password, rs.getString("password"))) {
					return new Account(rs.getString("username"), rs.getString("email"), rs.getString("password"),
							rs.getString("employee_id"), Roles.valueOf(rs.getString("role")));
				}
			}
			return null;
		}
	}

	public Account register(String username, String email, String password) throws SQLException, NoSuchAlgorithmException {
		if (accountWithEmailExists(email)) {
			throw new Error("Email already taken!");
		}

		String insertStatement = String.format("INSERT INTO accounts(username, email, password, role) VALUES('%s','%s','%s','%s')",
				username, email, passwordService.encrypt(password), Roles.VIEWER.name());
		try (Connection connection = ConnectionPool.getConnection();
				Statement statement = connection.createStatement()) {
			statement.executeUpdate(insertStatement);
		}
		return new Account(username,email, passwordService.encrypt(password),
				null, Roles.VIEWER);
	}

	private boolean accountWithEmailExists(String email) throws SQLException {

		try (Connection connection = ConnectionPool.getConnection();
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery("SELECT * FROM accounts WHERE email = '" + email + "'");) {
			if (rs.next()) {
				return true;
			}
			return false;
		}
	}
}
