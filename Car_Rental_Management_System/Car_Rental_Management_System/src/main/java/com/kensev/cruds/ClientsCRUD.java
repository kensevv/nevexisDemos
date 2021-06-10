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
import com.kensev.entitites.Vehicle;

public class ClientsCRUD {
	public List<Clients> getAllClients() throws SQLException, InterruptedException {
		return getAllClients("ID");
	}
	
	public List<Clients> getAllClients(String orderBy) throws SQLException, InterruptedException {
		List<Clients> allClients = new ArrayList<>(50);
		try (Connection connection = ConnectionPool.getConnection();
				Statement prepStatement = connection.createStatement();
				ResultSet resultSet = prepStatement.executeQuery("SELECT * FROM CLIENTS ORDER BY " + orderBy + " limit 100;");) {
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
			try (ResultSet rs = statement.executeQuery("SELECT * FROM CLIENTS WHERE 1<>1");) {
				rs.moveToInsertRow();
				rs.updateString("ID", newClient.getID());
				rs.updateString("FIRST_NAME", newClient.getFirst_name());
				rs.updateString("LAST_NAME", newClient.getLast_name());
				rs.updateString("EMAIL", newClient.getEmail());
				rs.updateString("PHONE", newClient.getPhone());
				rs.updateDate("BIRTHDAY", newClient.getBirthday());
				rs.updateString("DRIVER_LIC", newClient.getDriver_lic());
				rs.insertRow();
			}
		}
	}

	public void removeClient(String clientId) throws SQLException, InterruptedException {
		try (Connection connection = ConnectionPool.getConnection();
				PreparedStatement prepStatement = connection.prepareStatement(
						"SELECT * FROM CLIENTS WHERE ID = ?", ResultSet.TYPE_SCROLL_SENSITIVE,
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
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTS WHERE ID = ?",
						ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);) {
			statement.setString(1, client.getID());
			try (ResultSet rs = statement.executeQuery();) {
				if (rs.next()) {
					rs.updateString("ID", client.getID());
					rs.updateString("FIRST_NAME", client.getFirst_name());
					rs.updateString("LAST_NAME", client.getLast_name());
					rs.updateString("EMAIL", client.getEmail());
					rs.updateString("PHONE", client.getPhone());
					rs.updateDate("BIRTHDAY", client.getBirthday());
					rs.updateString("DRIVER_LIC", client.getDriver_lic());
					rs.updateRow();
				}
			}
		}
	}

	public Clients getClientById(String clientId) throws SQLException, InterruptedException {
		Clients foundClient = null;
		try (Connection connection = ConnectionPool.getConnection();
				PreparedStatement prepStatement = connection
						.prepareStatement("SELECT * FROM CLIENTS WHERE ID = ?")) {
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
