package com.kensev.cruds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kensev.connection.ConnectionPool;
import com.kensev.entitites.Vehicle;

public class VehicleCRUD {

	public List<Vehicle> listAllVehicles() throws SQLException {
		return listAllVehicles("licPlate");
	}

	public List<Vehicle> listAllVehicles(String orderBy) throws SQLException {

		List<Vehicle> allVehicles = new ArrayList<Vehicle>(50);

		try (Connection connection = ConnectionPool.getConnection();
				Statement prepStatement = connection.createStatement();
				ResultSet resultSet = prepStatement
						.executeQuery("SELECT * FROM vehicles ORDER BY " + orderBy + " limit 100;");) {
			while (resultSet.next()) {
				allVehicles.add(new Vehicle(resultSet.getString("licPlate"), resultSet.getString("model"),
						resultSet.getString("insurance"), resultSet.getBoolean("isAvailable"),
						resultSet.getInt("milleage"), resultSet.getDouble("price")));
			}
		}
		return allVehicles;
	}

	public void addVehicle(Vehicle newVehicle) throws SQLException {

		try (Connection connection = ConnectionPool.getConnection();
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_UPDATABLE);) {

			try (ResultSet rs = statement.executeQuery("SELECT * FROM vehicles WHERE 1<>1");) {
				rs.moveToInsertRow();
				rs.updateString("licPlate", newVehicle.getLicPlate());
				rs.updateString("model", newVehicle.getModel());
				rs.updateString("insurance", newVehicle.getInsurance());
				rs.updateBoolean("isAvailable", newVehicle.getIsAvailable());
				rs.updateInt("milleage", newVehicle.getMilleage());
				rs.updateDouble("price", newVehicle.getPrice());
				rs.insertRow();
			}
		}
	}

	public Vehicle getVehicle(String licPlate) throws SQLException {
		Vehicle foundVehicle = null;

		try (Connection connection = ConnectionPool.getConnection();
				PreparedStatement prepStatement = connection
						.prepareStatement("SELECT * FROM vehicles WHERE licPlate = ?")) {
			prepStatement.setString(1, licPlate);
			try (ResultSet resultSet = prepStatement.executeQuery()) {
				if (resultSet.next()) {
					foundVehicle = new Vehicle(licPlate, resultSet.getString("model"), resultSet.getString("milleage"),
							resultSet.getBoolean("isAvailable"), resultSet.getInt("milleage"),
							resultSet.getDouble("price"));
				}

			}
		}
		return foundVehicle;
	}

	public void removeVehicle(String licPlate) throws SQLException {
		
		try (Connection connection = ConnectionPool.getConnection();
				PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM vehicles WHERE licPlate = ?",
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			prepStatement.setString(1, licPlate);

			try (ResultSet rs = prepStatement.executeQuery()) {
				while (rs.next()) {
					rs.deleteRow();
				}
			}
		}
	}

	public void updateVehicle(Vehicle veh) throws SQLException {

		try (Connection connection = ConnectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM vehicles WHERE licPlate = ?",
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);) {
			statement.setString(1, veh.getLicPlate());
			try (ResultSet rs = statement.executeQuery();) {
				if (rs.next()) {
					rs.updateString("model", veh.getModel());
					rs.updateString("insurance", veh.getInsurance());
					rs.updateBoolean("isAvailable", veh.getIsAvailable());
					rs.updateInt("milleage", veh.getMilleage());
					rs.updateDouble("price", veh.getPrice());
					rs.updateRow();
				}

			}
		}
	}
}
