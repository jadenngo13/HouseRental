package com.jaden.login.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jaden.queries.SqlQueries;

public class LoginDao {

	Connection conn;
	ResultSet rs;
	PreparedStatement stmt;
	
	String url = "jdbc:mysql://localhost:3306/house_rental";
	String username = "root";
	String password = "J4soccer123!";
	
	public void getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean check(String uname, String pass) {
		try {
			stmt = conn.prepareStatement(SqlQueries.sqlGetUser);
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
			stmt = conn.prepareStatement(SqlQueries.sqlGetType);
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
}
