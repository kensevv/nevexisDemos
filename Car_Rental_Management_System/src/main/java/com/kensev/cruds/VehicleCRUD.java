package com.kensev.cruds;

import com.kensev.entitites.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class VehicleCRUD {

	public List<Vehicle> listAllVehicles() throws SQLException {
		return listAllVehicles("licPlate");
	}

	public List<Vehicle> listAllVehicles(String orderBy) throws SQLException {
		if (orderBy == null)
			orderBy = "licPlate";
		String queryStatement = String.format("SELECT * FROM vehicles ORDER BY %s limit 100;", orderBy);

		Connection connection = ConnectionManager.getConnection();
		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(queryStatement);) {
			List<Vehicle> allVehicles = new ArrayList<Vehicle>(50);
			while (resultSet.next()) {
				String licPlate = resultSet.getString("licPlate");
				String model = resultSet.getString("model");
				String insurance = resultSet.getString("insurance");
				boolean isAvailable = resultSet.getBoolean("isAvailable");
				int milleage = resultSet.getInt("milleage");
				double price = resultSet.getDouble("price");

				Vehicle newVehicle = new Vehicle(licPlate, model, insurance, isAvailable, milleage, price);
				allVehicles.add(newVehicle);
			}
			return allVehicles;
		}
	}

	public void addVehicle(Vehicle newVehicle) throws SQLException {
		Connection connection = ConnectionManager.getConnection();
		connection.setAutoCommit(false);
		
		try (Statement statement = connection.createStatement();) {
			String updateStatement = String.format(Locale.US,
					"INSERT INTO vehicles(licPlate, model, insurance, isAvailable, milleage, price)"
							+ " VALUES('%s', '%s', '%s', %b, %d, %f)",
					newVehicle.getLicPlate(), newVehicle.getModel(), newVehicle.getInsurance(),
					newVehicle.getIsAvailable(), newVehicle.getMilleage(), newVehicle.getPrice());
			statement.executeUpdate(updateStatement);
			
			connection.commit();
		} catch (Exception e)
		{
			connection.rollback();
		}
	}

	public Vehicle getVehicle(String licPlate) throws SQLException {
		String queryStatement = String.format("Select * FROM vehicles WHERE licPlate = '%s'", licPlate);
		Vehicle foundVehicle = null;

		Connection connection = ConnectionManager.getConnection();
		
		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(queryStatement);) {
			if (resultSet.next()) {
				String model = resultSet.getString("model");
				String insurance = resultSet.getString("insurance");
				boolean isAvailable = resultSet.getBoolean("isAvailable");
				int milleage = resultSet.getInt("milleage");
				double price = resultSet.getDouble("price");

				foundVehicle = new Vehicle(licPlate, model, insurance, isAvailable, milleage, price);
			}
			return foundVehicle;
		} 
	}

	public void removeVehicle(String licPlate) throws SQLException {
		Connection connection = ConnectionManager.getConnection();
		connection.setAutoCommit(false);
		
		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate(String.format("DELETE FROM vehicles WHERE licPlate = '%s'", licPlate));
			
			connection.commit();
		} catch (Exception e) {
			connection.rollback();
		}
	}

	public void updateVehicle(Vehicle veh) throws SQLException {
		Connection connection = ConnectionManager.getConnection();
		connection.setAutoCommit(false);
		
		try (Statement statement = connection.createStatement()) {
			String updateStatement = String.format(Locale.US,
					"UPDATE vehicles SET model = '%s', insurance = '%s', isAvailable = %b, milleage = %d, price = %.2f WHERE licPlate = '%s';",
					veh.getModel(), veh.getInsurance(), veh.getIsAvailable(), veh.getMilleage(), veh.getPrice(), veh.getLicPlate());
			statement.executeUpdate(updateStatement);
			
			connection.commit();
		} catch (Exception e)
		{
			connection.rollback();
		}
	}
}
