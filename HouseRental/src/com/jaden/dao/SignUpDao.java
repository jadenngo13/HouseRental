package com.jaden.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jaden.connection.DBConnection;
import com.jaden.queries.SqlQueries;

public class SignUpDao {

	ResultSet rs;
	PreparedStatement stmt;
	
	public void insertEntry(String username, String password, String email, String type) {
		// Create new login for user
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlInsertUser);
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, type);
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Get inserted user's id
		int id;
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetLastUser);
			stmt.setString(1, username);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			if (rs.next())
				id = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Insert user into appropriate table
		String sqlQuery = "";
		switch(type) {
		case "owners":
			sqlQuery = SqlQueries.sqlInsertOwner;
			break;
		case "customers":
			sqlQuery = SqlQueries.sqlInsertCustomer;
			break;
		default:
			System.out.println("No valid type given for insert.");
			break;
		}
		
		// customers(first_name, last_name, gender, birthday, email, rentals)
		try {
			stmt = DBConnection.conn.prepareStatement(sqlQuery);
			stmt.setString(1, username);
			stmt.setString(2, "");
			stmt.setString(3, "U");
			stmt.setString(4, null);
			stmt.setString(5, "");
			stmt.setString(6, "");
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
