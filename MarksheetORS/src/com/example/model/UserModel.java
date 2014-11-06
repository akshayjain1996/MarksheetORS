package com.example.model;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.adapter.DBAdapter;
import com.example.dto.UserDTO;

public class UserModel {

	private Context context = null;

	public UserModel(Context context) {
		this.context = context;
	}

	public void add(UserDTO userDTO) throws Exception {
		ContentValues values = new ContentValues();
		values.put(UserDTO.ID, nextPK());
		values.put(UserDTO.NAME, userDTO.getName());
		values.put(UserDTO.USER_NAME, userDTO.getUserName());
		values.put(UserDTO.PASSWORD, userDTO.getPassword());

		DBAdapter adapter = DBAdapter.getDBAdapterInstance(context);
		adapter.createDataBase();
		adapter.openDataBase();
		adapter.insertRecordsInDB(UserDTO.DB, null, values);
		adapter.close();
	}

	public int nextPK() throws Exception {
		DBAdapter adapter = DBAdapter.getDBAdapterInstance(context);
		adapter.createDataBase();
		adapter.openDataBase();
		String query = "SELECT MAX(ID) FROM USER";
		Cursor cursor = adapter.selectRecordsFromDB(query, null);
		int pk = 1;
		if (cursor.moveToNext()) {
			pk += cursor.getInt(0);
		}
		adapter.close();
		return pk;
	}

	public UserDTO authenticate(String login, String password) throws Exception {
		DBAdapter adapter = DBAdapter.getDBAdapterInstance(context);
		adapter.createDataBase();
		adapter.openDataBase();
		String query = "SELECT * FROM USER WHERE USER_NAME = '" + login
				+ "' AND PASSWORD = '" + password + "'";
		UserDTO dto = null;
		Cursor cursor = adapter.selectRecordsFromDB(query, null);

		if (cursor.moveToNext()) {
			dto = new UserDTO();
			dto.setId(cursor.getInt(0));
			dto.setName(cursor.getString(1));
			dto.setUserName(cursor.getString(2));
			dto.setPassword(cursor.getString(3));
		}
		adapter.close();
		return dto;
	}

	public List list() throws Exception {
		DBAdapter adapter = DBAdapter.getDBAdapterInstance(context);
		adapter.createDataBase();
		adapter.openDataBase();
		String query = "SELECT * FROM USER";
		Cursor cursor = adapter.selectRecordsFromDB(query, null);

		List list = new ArrayList();

		if (cursor.moveToNext()) {
			UserDTO dto = new UserDTO();
			dto.setId(cursor.getInt(0));
			dto.setName(cursor.getString(1));
			dto.setUserName(cursor.getString(2));
			dto.setPassword(cursor.getString(3));
			list.add(dto);
		}
		adapter.close();
		return list;
	}

}