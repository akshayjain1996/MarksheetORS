package com.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.dto.UserDTO;

public class UserService {

	public int nextPK() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/marksheetors", "root", "root");
		PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT MAX(ID) FROM USER");
		ResultSet resultSet = preparedStatement.executeQuery();
		int pk = 1;
		if (resultSet.next()) {
			pk += resultSet.getInt(1);
		}
		return pk;
	}

	public void add(UserDTO userDTO) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/marksheetors", "root", "root");
		PreparedStatement preparedStatement = connection
				.prepareStatement("INSERT INTO USER VALUES(?,?,?,?)");
		preparedStatement.setInt(1, nextPK());
		preparedStatement.setString(2, userDTO.getName());
		preparedStatement.setString(3, userDTO.getUserName());
		preparedStatement.setString(4, userDTO.getPassword());
		int i = preparedStatement.executeUpdate();
		System.out.println("Value added " + i);
		preparedStatement.close();
	}

	public UserDTO authenticate(String login, String password) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/marksheetors", "root", "root");
		PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT * FROM USER WHERE USER_NAME = ? AND PASSWORD = ?");
		preparedStatement.setString(1, login);
		preparedStatement.setString(2, password);
		ResultSet resultSet = preparedStatement.executeQuery();
		UserDTO dto = null;
		if (resultSet.next()) {
			dto = new UserDTO();
			dto.setId(resultSet.getInt(1));
			dto.setName(resultSet.getString(2));
			dto.setUserName(resultSet.getString(3));
			dto.setPassword(resultSet.getString(4));
		}
		preparedStatement.close();
		return dto;
	}

}