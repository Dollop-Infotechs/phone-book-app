package com.dollop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.dollop.model.Contact;
import com.dollop.util.DBConnectionUtil;

public class ContactInterfaceDAOImpl implements ContactInterfaceDAO {

	Connection connection = null;
	ResultSet resultSet = null;
	Statement statement = null;
	PreparedStatement preparedStatement = null;

	@Override
	public boolean createContact(Contact contact) {
		boolean flag = false;
		try {
			String sql = "INSERT INTO contact(name, email, phone)VALUES" + "('" + contact.getName() + "', '"
					+ contact.getEmail() + "', '" + contact.getPhone() + "')";
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<Contact> viewAllContact() {

		List<Contact> list = null;
		Contact contact = null;

		try {

			list = new ArrayList<Contact>();
			String sql = "SELECT * FROM contact";
			connection = DBConnectionUtil.openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				contact = new Contact();
				contact.setId(resultSet.getInt("id"));
				contact.setName(resultSet.getString("name"));
				contact.setEmail(resultSet.getString("email"));
				contact.setPhone(resultSet.getBigDecimal("phone").toBigInteger());
				list.add(contact);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Contact viewContactById(Integer id) {
		Contact contact = null;

		try {

			String sql = "SELECT * FROM contact where id="+id;
			connection = DBConnectionUtil.openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				contact = new Contact();
				contact.setId(resultSet.getInt("id"));
				contact.setName(resultSet.getString("name"));
				contact.setEmail(resultSet.getString("email"));
				contact.setPhone(resultSet.getBigDecimal("phone").toBigInteger());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contact;
	}

	@Override
	public boolean updateContact(Contact contact) {
		boolean flag = false;
		try {
			String sql = "UPDATE contact SET name = '"+contact.getName()+"', "
					+ "email = '"+contact.getEmail()+"', phone = '"+contact.getPhone()+"' where id="+contact.getId();
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean deleteContact(Integer id) {
		boolean flag = false;
		try {
			String sql = "DELETE FROM contact where id="+id;
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

}
