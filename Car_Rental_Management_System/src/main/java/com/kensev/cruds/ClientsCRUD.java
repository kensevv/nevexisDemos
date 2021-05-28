package com.kensev.cruds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kensev.connection.ConnectionPool;
import com.kensev.entitites.Clients;

public class ClientsCRUD {
	public List<Clients> getAllClients() throws SQLException, InterruptedException {
		List<Clients> allClients = new ArrayList<>(50);
		try (Connection connection = ConnectionPool.getConnection();
				Statement prepStatement = connection.createStatement();
				ResultSet resultSet = prepStatement.executeQuery("SELECT * FROM FN71947.CLIENTS");) {
			while (resultSet.next()) {
				allClients.add(new Clients(resultSet.getString("ID"), resultSet.getString("FIRST_NAME"),
						resultSet.getString("LAST_NAME"), resultSet.getString("EMAIL"), resultSet.getString("PHONE"),
						resultSet.getDate("BIRTHDAY"), resultSet.getString("DRIVER_LIC")));
			}
		}
		return allClients;
	}

	public void addClient(Clients newClient) throws SQLException, InterruptedException {
		try (Connection connection = ConnectionPool.getConnection();
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);) {
			try (ResultSet rs = statement.executeQuery("SELECT * FROM FN71947.CLIENTS WHERE 1<>1");) {
				rs.moveToInsertRow();
				rs.updateString("ID", newClient.getId());
				rs.updateString("FIRST_NAME", newClient.getFirstName());
				rs.updateString("LAST_NAME", newClient.getLastName());
				rs.updateString("EMAIL", newClient.getEmail());
				rs.updateString("PHONE", newClient.getPhone());
				rs.updateDate("BIRTHDAY", newClient.getBirthday());
				rs.updateString("DRIVER_LIC", newClient.getDriverLic());
				rs.insertRow();
			}
		}
	}

	public void removeClient(String clientId) throws SQLException, InterruptedException {
		try (Connection connection = ConnectionPool.getConnection();
				PreparedStatement prepStatement = connection.prepareStatement(
						"SELECT * FROM FN71947.CLIENTS WHERE ID = ?", ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE)) {
			prepStatement.setString(1, clientId);
			try (ResultSet rs = prepStatement.executeQuery()) {
				while (rs.next()) {
					rs.deleteRow();
				}
			}
		}
	}

	public void updateClient(Clients client) throws SQLException, InterruptedException {

		try (Connection connection = ConnectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM FN71947.CLIENTS WHERE ID = ?",
						ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);) {
			statement.setString(1, client.getId());
			try (ResultSet rs = statement.executeQuery();) {
				if (rs.next()) {
					rs.updateString("ID", client.getId());
					rs.updateString("FIRST_NAME", client.getFirstName());
					rs.updateString("LAST_NAME", client.getLastName());
					rs.updateString("EMAIL", client.getEmail());
					rs.updateString("PHONE", client.getPhone());
					rs.updateDate("BIRTHDAY", client.getBirthday());
					rs.updateString("DRIVER_LIC", client.getDriverLic());
					rs.updateRow();
				}
			}
		}
	}

	public Clients getClientById(String clientId) throws SQLException, InterruptedException {
		Clients foundClient = null;
		try (Connection connection = ConnectionPool.getConnection();
				PreparedStatement prepStatement = connection
						.prepareStatement("SELECT * FROM FN71947.CLIENTS WHERE ID = ?")) {
			prepStatement.setString(1, clientId);
			try (ResultSet resultSet = prepStatement.executeQuery()) {
				if (resultSet.next()) {
					foundClient = new Clients(resultSet.getString("ID"), resultSet.getString("FIRST_NAME"),
							resultSet.getString("LAST_NAME"), resultSet.getString("EMAIL"),
							resultSet.getString("PHONE"), resultSet.getDate("BIRTHDAY"),
							resultSet.getString("DRIVER_LIC"));
				}
			}
		}
		return foundClient;
	}
}
