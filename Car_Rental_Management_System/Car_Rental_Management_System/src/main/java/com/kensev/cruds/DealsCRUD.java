package com.kensev.cruds;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kensev.connection.ConnectionPool;
import com.kensev.entitites.Deals;

public class DealsCRUD {
	public List<Deals> listAllDeals() throws SQLException, InterruptedException{
		return listAllDeals("start_date");
	}
	
	public List<Deals> listAllDeals(String orderBy) throws SQLException, InterruptedException {
		List<Deals> allDeals = new ArrayList<Deals>(50);
		try (Connection connection = ConnectionPool.getConnection();
				Statement prepStatement = connection.createStatement();
				ResultSet resultSet = prepStatement.executeQuery("SELECT * FROM DEALS ORDER BY " +orderBy +  " limit 100;");) {
			while (resultSet.next()) {
				allDeals.add(new Deals(resultSet.getDate("START_DATE"), resultSet.getString("CLIENT_ID"),
						resultSet.getString("EMPLOYEE_ID"), resultSet.getString("VEHICLE_LICPLATE"),
						resultSet.getString("BRANCH_NAME"), resultSet.getDate("END_DATE"),
						resultSet.getDouble("PAYMENT")));
			}
		}
		return allDeals;
	}

	public void addDeal(Deals newDeal) throws SQLException, InterruptedException {

		try (Connection connection = ConnectionPool.getConnection();
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);) {

			try (ResultSet rs = statement.executeQuery("SELECT * FROM DEALS WHERE 1<>1");) {
				rs.moveToInsertRow();
				rs.updateDate("START_DATE", newDeal.getStart_date());
				rs.updateString("CLIENT_ID", newDeal.getClient_id());
				rs.updateString("EMPLOYEE_ID", newDeal.getEmployee_id());
				rs.updateString("VEHICLE_LICPLATE", newDeal.getVehicle_licPlate());
				rs.updateString("BRANCH_NAME", newDeal.getBranch_name());
				rs.updateDate("END_DATE", newDeal.getEnd_date());
				rs.updateDouble("PAYMENT", newDeal.getPayment());
				rs.insertRow();
			}
		}
	}

	public Deals getDealById(Date startDate, String clientId, String employeeId, String vehicleLicPlate,
			String branchName) throws SQLException, InterruptedException {
		Deals foundDeal = null;

		try (Connection connection = ConnectionPool.getConnection();
				PreparedStatement prepStatement = connection.prepareStatement(
						"SELECT * FROM DEALS WHERE START_DATE = ? AND CLIENT_ID = ? AND EMPLOYEE_ID = ? AND VEHICLE_LICPLATE = ? AND BRANCH_NAME = ?")) {
			prepStatement.setDate(1, startDate);
			prepStatement.setString(2, clientId);
			prepStatement.setString(3, employeeId);
			prepStatement.setString(4, vehicleLicPlate);
			prepStatement.setString(5, branchName);
			try (ResultSet resultSet = prepStatement.executeQuery()) {
				if (resultSet.next()) {
					foundDeal = new Deals(resultSet.getDate("START_DATE"), resultSet.getString("CLIENT_ID"),
							resultSet.getString("EMPLOYEE_ID"), resultSet.getString("VEHICLE_LICPLATE"),
							resultSet.getString("BRANCH_NAME"), resultSet.getDate("END_DATE"),
							resultSet.getDouble("PAYMENT"));
				}
			}
		}
		return foundDeal;
	}

	public void removeDeal(Date startDate, String clientId, String employeeId, String vehicleLicPlate,
			String branchName) throws SQLException, InterruptedException {

		try (Connection connection = ConnectionPool.getConnection();
				PreparedStatement prepStatement = connection.prepareStatement(
						"SELECT * FROM DEALS WHERE START_DATE = ? AND CLIENT_ID = ? AND EMPLOYEE_ID = ? AND VEHICLE_LICPLATE = ? AND BRANCH_NAME = ?",
						ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			prepStatement.setDate(1, startDate);
			prepStatement.setString(2, clientId);
			prepStatement.setString(3, employeeId);
			prepStatement.setString(4, vehicleLicPlate);
			prepStatement.setString(5, branchName);
			try (ResultSet rs = prepStatement.executeQuery()) {
				while (rs.next()) {
					rs.deleteRow();
				}
			}
		}
	}

	public void updateDeal(Deals deal) throws SQLException, InterruptedException {

		try (Connection connection = ConnectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"SELECT * FROM DEALS WHERE START_DATE = ? AND CLIENT_ID = ? AND EMPLOYEE_ID = ? AND VEHICLE_LICPLATE = ? AND BRANCH_NAME = ?",
						ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);) {
			statement.setDate(1, deal.getStart_date());
			statement.setString(2, deal.getClient_id());
			statement.setString(3, deal.getEmployee_id());
			statement.setString(4, deal.getVehicle_licPlate());
			statement.setString(5, deal.getBranch_name());
			try (ResultSet rs = statement.executeQuery()) {
				if (rs.next()) {
					rs.updateDate("START_DATE", deal.getStart_date());
					rs.updateString("CLIENT_ID", deal.getClient_id());
					rs.updateString("EMPLOYEE_ID", deal.getEmployee_id());
					rs.updateString("VEHICLE_LICPLATE", deal.getVehicle_licPlate());
					rs.updateString("BRANCH_NAME", deal.getBranch_name());
					rs.updateDate("END_DATE", deal.getEnd_date());
					rs.updateDouble("PAYMENT", deal.getPayment());
					rs.updateRow();
				}
			}
		}
	}
}
