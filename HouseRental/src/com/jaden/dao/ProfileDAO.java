package com.jaden.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jaden.connection.DBConnection;
import com.jaden.queries.SqlQueries;

public class ProfileDAO {
	PreparedStatement stmt;
	ResultSet rs;
	
	public void saveUser(int id, String firstName, String lastName, String username, String email, String bday, String type) {
		try {
			switch(type) {
			case "admins":
				stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlUpdateAdmin);
				break;
			case "owners":
				stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlUpdateOwner);
				break;
			case "customers":
				stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlUpdateCustomer);
				break;
			default:
				break;
			}
			System.out.println("valuess: " + firstName + " " + lastName + " " + username + " " + email + " " + bday);
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			stmt.setString(3, bday);
			stmt.setString(4, email);
			stmt.setInt(5, id);
			stmt.execute();
			
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlUpdateUser);
			stmt.setString(1, username);
			stmt.setInt(2, id);
			stmt.setString(3, type);
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
