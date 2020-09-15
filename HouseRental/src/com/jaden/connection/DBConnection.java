package com.jaden.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	public static Connection conn;
	
	// Change to fit login credentials
	public static final String url = "jdbc:mysql://localhost:3306/house_rental";
	public static final String username = "root";
	public static final String password = "J4soccer123!";
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
