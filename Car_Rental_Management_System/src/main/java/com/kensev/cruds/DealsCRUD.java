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
	public List<Deals> listAllDeals() throws SQLException, InterruptedException {
		List<Deals> allDeals = new ArrayList<Deals>(50);
		try (Connection connection = ConnectionPool.getConnection();
				Statement prepStatement = connection.createStatement();
				ResultSet resultSet = prepStatement.executeQuery("SELECT * FROM FN71947.DEALS");) {
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

			try (ResultSet rs = statement.executeQuery("SELECT * FROM FN71947.DEALS WHERE 1<>1");) {
				rs.moveToInsertRow();
				rs.updateDate("START_DATE", newDeal.getStartDate());
				rs.updateString("CLIENT_ID", newDeal.getClientId());
				rs.updateString("EMPLOYEE_ID", newDeal.getEmlpoyeeId());
				rs.updateString("VEHICLE_LICPLATE", newDeal.getVehicleLicPlate());
				rs.updateString("BRANCH_NAME", newDeal.getBranchName());
				rs.updateDate("END_DATE", newDeal.getEndDate());
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
						"SELECT * FROM FN71947.DEALS WHERE START_DATE = ? AND CLIENT_ID = ? AND EMPLOYEE_ID = ? AND VEHICLE_LICPLATE = ? AND BRANCH_NAME = ?")) {
			prepStatement.setDate(1, startDate);
			prepStatement.setString(2, clientId);
			prepStatement.setString(3, vehicleLicPlate);
			prepStatement.setString(4, branchName);
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
						"SELECT * FROM FN71947.DEALS WHERE START_DATE = ? AND CLIENT_ID = ? AND EMPLOYEE_ID = ? AND VEHICLE_LICPLATE = ? AND BRANCH_NAME = ?",
						ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			prepStatement.setDate(1, startDate);
			prepStatement.setString(2, clientId);
			prepStatement.setString(3, vehicleLicPlate);
			prepStatement.setString(4, branchName);
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
						"SELECT * FROM FN71947.DEALS WHERE START_DATE = ? AND CLIENT_ID = ? AND EMPLOYEE_ID = ? AND VEHICLE_LICPLATE = ? AND BRANCH_NAME = ?",
						ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);) {
			statement.setDate(1, deal.getStartDate());
			statement.setString(2, deal.getClientId());
			statement.setString(3, deal.getVehicleLicPlate());
			statement.setString(4, deal.getBranchName());
			try (ResultSet rs = statement.executeQuery();) {
				if (rs.next()) {
					rs.updateDate("START_DATE", deal.getStartDate());
					rs.updateString("CLIENT_ID", deal.getClientId());
					rs.updateString("EMPLOYEE_ID", deal.getEmlpoyeeId());
					rs.updateString("VEHICLE_LICPLATE", deal.getVehicleLicPlate());
					rs.updateString("BRANCH_NAME", deal.getBranchName());
					rs.updateDate("END_DATE", deal.getEndDate());
					rs.updateDouble("PAYMENT", deal.getPayment());
					rs.updateRow();
				}
			}
		}
	}
}
