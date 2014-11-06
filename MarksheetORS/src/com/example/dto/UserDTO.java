package com.example.dto;

public class UserDTO {

	public static final String DB = "user";
	public static final String ID = "ID";
	public static final String NAME = "NAME";
	public static final String USER_NAME = "USER_NAME";
	public static final String PASSWORD = "PASSWORD";

	private int id;
	private String name;
	private String userName;
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}