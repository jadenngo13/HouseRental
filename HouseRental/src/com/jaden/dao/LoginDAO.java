package com.jaden.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jaden.connection.DBConnection;
import com.jaden.sql.SqlQueries;

public class LoginDao {

	ResultSet rs;
	PreparedStatement stmt;
	
	public boolean check(String uname, String pass) {
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetUserFromLogin);
			stmt.setString(1, uname);
			stmt.setString(2, pass);
			rs = stmt.executeQuery();
			
			if (rs.next())
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String getType(String uname, String pass) {
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetType);
			stmt.setString(1, uname);
			stmt.setString(2, pass);
			rs = stmt.executeQuery();
			if (rs.next())
				return rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";	
	}
	
	public int getID(String uname, String type) {
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetUserID);
			stmt.setString(1, uname);
			stmt.setString(2, type);
			rs = stmt.executeQuery();
			if (rs.next())
				return rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public String getFirstName(int id, String type) {
		try {
			switch (type) {
			case "admins":
				stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetAdminFName);
				break;
			case "owners":
				stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetOwnerFName);
				break;
			case "customers":
				stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetCustomerFName);
				break;
			default:
				break;
			}
					
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) 
				return rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	public String getLastName(int id, String type) {
		try {
			switch (type) {
			case "admins":
				stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetAdminLName);
				break;
			case "owners":
				stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetOwnerLName);
				break;
			case "customers":
				stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetCustomerLName);
				break;
			default:
				break;
			}
					
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) 
				return rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	public String getEmail(int id, String type) {
		try {
			switch (type) {
			case "admins":
				stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetAdminEmail);
				break;
			case "owners":
				stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetOwnerEmail);
				break;
			case "customers":
				stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetCustomerEmail);
				break;
			default:
				break;
			}
					
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) 
				return rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	public String getBirthday(int id, String type) {
		try {
			switch (type) {
			case "admins":
				stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetAdminBirthday);
				break;
			case "owners":
				stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetOwnerBirthday);
				break;
			case "customers":
				stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetCustomerBirthday);
				break;
			default:
				break;
			}
					
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) 
				return rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	

	public String getUsername(int id, String type) {
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetUserUsername);
			stmt.setInt(1, id);
			stmt.setString(2, type);
			rs = stmt.executeQuery();
			if (rs.next()) 
				return rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	public String getPassword(int id, String type) {
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetUserPassword);
			stmt.setInt(1, id);
			stmt.setString(2, type);
			rs = stmt.executeQuery();
			if (rs.next()) 
				return rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	public String getRentedString(int id, String type) throws SQLException {
		String result = null;
		switch (type) {
		case "owners":
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetOwnerRentals);
			break;
		case "customers":
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetCustomerRentals);
			break;
		default:
			break;
		}
		stmt.setInt(1, id);
		rs = stmt.executeQuery();
		if (rs.next()) {
			result = rs.getString(1);
		}
		return result;
	}
}
