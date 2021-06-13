package com.kensev.cruds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kensev.connection.ConnectionPool;
import com.kensev.entitites.Employees;

public class EmployeesCRUD {
	public List<Employees> getAllEmployees() throws SQLException, InterruptedException{
		return getAllEmployees("ID");
	}
	public List<Employees> getAllEmployees(String orderBy) throws SQLException, InterruptedException {
		List<Employees> allEmployees = new ArrayList<>(50);
		try (Connection connection = ConnectionPool.getConnection();
				Statement prepStatement = connection.createStatement();
				ResultSet resultSet = prepStatement.executeQuery("SELECT * FROM EMPLOYEES ORDER BY " + orderBy + " LIMIT 100");) {
			while (resultSet.next()) {
				allEmployees.add(new Employees(resultSet.getString("ID"), resultSet.getString("FIRST_NAME"),
						resultSet.getString("LAST_NAME"), resultSet.getString("EMAIL"), resultSet.getString("PHONE"),
						resultSet.getDate("BIRTHDAY"), resultSet.getString("WORK_NUMBER"),
						resultSet.getString("BRANCH_NAME"), resultSet.getString("MANAGER_ID")));
			}
		}
		return allEmployees;
	}

	public void addEmployee(Employees newEmployee) throws SQLException, InterruptedException {
		try (Connection connection = ConnectionPool.getConnection();
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);) {
			try (ResultSet rs = statement.executeQuery("SELECT * FROM EMPLOYEES WHERE 1<>1");) {
				rs.moveToInsertRow();
				rs.updateString("ID", newEmployee.getID());
				rs.updateString("FIRST_NAME", newEmployee.getFirst_name());
				rs.updateString("LAST_NAME", newEmployee.getLast_name());
				rs.updateString("EMAIL", newEmployee.getEmail());
				rs.updateString("PHONE", newEmployee.getPhone());
				rs.updateDate("BIRTHDAY", newEmployee.getBirthday());
				rs.updateString("WORK_NUMBER", newEmployee.getWork_number());
				rs.updateString("BRANCH_NAME", newEmployee.getBranch_name());
				rs.updateString("MANAGER_ID", newEmployee.getManager_id());
				rs.insertRow();
			}
		}
	}

	public void removeEmployee(String employeeId) throws SQLException, InterruptedException {
		try (Connection connection = ConnectionPool.getConnection();
				PreparedStatement prepStatement = connection.prepareStatement(
						"SELECT * FROM EMPLOYEES WHERE ID = ?", ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE)) {
			prepStatement.setString(1, employeeId);
			try (ResultSet rs = prepStatement.executeQuery()) {
				while (rs.next()) {
					rs.deleteRow();
				}
			}
		}
	}

	public void updateEmployee(Employees employee) throws SQLException, InterruptedException {

		try (Connection connection = ConnectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"SELECT * FROM EMPLOYEES WHERE ID = ?", ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);) {
			statement.setString(1, employee.getID());
			try (ResultSet rs = statement.executeQuery();) {
				if (rs.next()) {
					rs.updateString("ID", employee.getID());
					rs.updateString("FIRST_NAME", employee.getFirst_name());
					rs.updateString("LAST_NAME", employee.getLast_name());
					rs.updateString("EMAIL", employee.getEmail());
					rs.updateString("PHONE", employee.getPhone());
					rs.updateDate("BIRTHDAY", employee.getBirthday());
					rs.updateString("WORK_NUMBER", employee.getWork_number());
					rs.updateString("BRANCH_NAME", employee.getBranch_name());
					rs.updateString("MANAGER_ID", employee.getManager_id());
					rs.updateRow();
				}
			}
		}
	}

	public Employees getEmployeeById(String employeeId) throws SQLException, InterruptedException {
		Employees foundEmployee = null;
		try (Connection connection = ConnectionPool.getConnection();
				PreparedStatement prepStatement = connection
						.prepareStatement("SELECT * FROM EMPLOYEES WHERE ID = ?")) {
			prepStatement.setString(1, employeeId);
			try (ResultSet resultSet = prepStatement.executeQuery()) {
				if (resultSet.next()) {
					foundEmployee = new Employees(resultSet.getString("ID"), resultSet.getString("FIRST_NAME"),
							resultSet.getString("LAST_NAME"), resultSet.getString("EMAIL"),
							resultSet.getString("PHONE"), resultSet.getDate("BIRTHDAY"),
							resultSet.getString("WORK_NUMBER"), resultSet.getString("BRANCH_NAME"),
							resultSet.getString("MANAGER_ID"));
				}
			}
		}
		return foundEmployee;
	}
}
