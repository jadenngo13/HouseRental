package com.jaden.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jaden.connection.DBConnection;
import com.jaden.queries.SqlQueries;

public class SignUpDao {

	ResultSet rs;
	PreparedStatement stmt;
	
	public void insertEntry(String username, String password, String email, String type) {		
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
			stmt.setString(3, null);
			stmt.setString(4, email);
			stmt.setString(5, "");
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		// Get inserted user's id
		int id = -1;
		try {
			if (type.equals("owners")) {
				stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetRecentOwnerID);
			}if (type.equals("customers")) {
				stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetRecentCustomerID);
			}
			rs = stmt.executeQuery();
			if (rs.next())
				id = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Create new login for user
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlInsertUser);
			stmt.setInt(1, id);
			stmt.setString(2, username);
			stmt.setString(3, password);
			stmt.setString(4, type);
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
