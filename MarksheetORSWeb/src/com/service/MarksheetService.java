package com.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dto.MarksheetDTO;
import com.dto.UserDTO;

public class MarksheetService {

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

	public List list() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/marksheetors", "root", "root");
		PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT * FROM MARKSHEET");
		ResultSet resultSet = preparedStatement.executeQuery();
		MarksheetDTO dto = null;
		List list = new ArrayList();
		while (resultSet.next()) {
			dto = new MarksheetDTO();
			dto.setRollno(resultSet.getInt(1));
			dto.setName(resultSet.getString(2));
			dto.setPhysics(resultSet.getInt(3));
			dto.setChemistry(resultSet.getInt(4));
			dto.setMaths(resultSet.getInt(5));
			list.add(dto);
		}
		preparedStatement.close();
		return list;
	}

}