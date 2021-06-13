package com.kensev.cruds;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kensev.connection.ConnectionPool;
import com.kensev.entitites.ContactUs;

public class ContactUsCRUD {

	public List<ContactUs> listAllContactUs() throws SQLException {
		return listAllContactUs("name");
	}

	public List<ContactUs> listAllContactUs(String orderBy) throws SQLException {

		List<ContactUs> allContactUs = new ArrayList<ContactUs>(50);

		try (Connection connection = ConnectionPool.getConnection();
				Statement prepStatement = connection.createStatement();
				ResultSet resultSet = prepStatement
						.executeQuery("SELECT * FROM contact_us ORDER BY " + orderBy + " limit 100;");) {
			while (resultSet.next()) {
				allContactUs.add(new ContactUs(resultSet.getInt("ID"), resultSet.getString("name"),
						resultSet.getString("email"), resultSet.getString("message")));
			}
		}
		return allContactUs;
	}

	public void addContactUs(ContactUs newContactUs) throws SQLException {

		try (Connection connection = ConnectionPool.getConnection();
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_UPDATABLE);) {

			try (ResultSet rs = statement.executeQuery("SELECT * FROM contact_us WHERE 1<>1");) {
				rs.moveToInsertRow();
				rs.updateString("name", newContactUs.getName());
				rs.updateString("email", newContactUs.getEmail());
				rs.updateString("message", newContactUs.getMessage());
				rs.insertRow();
			}
		}
	}
}