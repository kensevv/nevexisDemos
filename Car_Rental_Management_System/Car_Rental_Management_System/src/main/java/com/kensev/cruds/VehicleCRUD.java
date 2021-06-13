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
		return listAllVehicles("license_plate");
	}

	public List<Vehicle> listAllVehicles(String orderBy) throws SQLException {

		List<Vehicle> allVehicles = new ArrayList<Vehicle>(50);

		try (Connection connection = ConnectionPool.getConnection();
				Statement prepStatement = connection.createStatement();
				ResultSet resultSet = prepStatement
						.executeQuery("SELECT * FROM vehicles ORDER BY " + orderBy + " limit 100;");) {
			while (resultSet.next()) {
				allVehicles.add(new Vehicle(resultSet.getString("license_plate"), resultSet.getString("model"),
						resultSet.getString("insurance"), resultSet.getBoolean("is_available"),
						resultSet.getInt("mileage"), resultSet.getDouble("price"), resultSet.getString("branch_name")));
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
				rs.updateString("license_plate", newVehicle.getLicense_plate());
				rs.updateString("model", newVehicle.getModel());
				rs.updateString("insurance", newVehicle.getInsurance());
				rs.updateBoolean("is_available", newVehicle.getIs_available());
				rs.updateInt("mileage", newVehicle.getMileage());
				rs.updateDouble("price", newVehicle.getPrice());
				rs.updateString("branch_name", newVehicle.getBranch_name());
				rs.insertRow();
			}
		}
	}

	public Vehicle getVehicle(String licPlate) throws SQLException {
		Vehicle foundVehicle = null;

		try (Connection connection = ConnectionPool.getConnection();
				PreparedStatement prepStatement = connection
						.prepareStatement("SELECT * FROM vehicles WHERE license_plate = ?")) {
			prepStatement.setString(1, licPlate);
			try (ResultSet resultSet = prepStatement.executeQuery()) {
				if (resultSet.next()) {
					foundVehicle = new Vehicle(licPlate, resultSet.getString("model"), resultSet.getString("insurance"),
							resultSet.getBoolean("is_available"), resultSet.getInt("mileage"),
							resultSet.getDouble("price"), resultSet.getString("branch_name"));
				}

			}
		}
		return foundVehicle;
	}

	public void removeVehicle(String licPlate) throws SQLException {
		
		try (Connection connection = ConnectionPool.getConnection();
				PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM vehicles WHERE license_plate = ?",
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
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM vehicles WHERE license_plate = ?",
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);) {
			statement.setString(1, veh.getLicense_plate());
			try (ResultSet rs = statement.executeQuery();) {
				if (rs.next()) {
					rs.updateString("model", veh.getModel());
					rs.updateString("insurance", veh.getInsurance());
					rs.updateBoolean("is_available", veh.getIs_available());
					rs.updateInt("mileage", veh.getMileage());
					rs.updateDouble("price", veh.getPrice());
					rs.updateString("branch_name", veh.getBranch_name());
					rs.updateRow();
				}
			}
		}
	}
}
