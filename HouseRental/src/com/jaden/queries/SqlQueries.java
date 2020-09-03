package com.jaden.queries;

public class SqlQueries {
	
	public final static String sqlGetUser = "SELECT * FROM logins WHERE username=? and password=?";
	public final static String sqlGetType = "SELECT user_type FROM logins WHERE username=? and password=?";
	public final static String sqlGetUserID = "SELECT id FROM logins WHERE username=? and password=?";
	public final static String sqlGetAdminFName = "SELECT first_name FROM customers WHERE id=?";
	public final static String sqlGetOwnerFName = "SELECT first_name FROM customers WHERE id=?";
	public final static String sqlGetCustomerFName = "SELECT first_name FROM customers WHERE id=?";
	public final static String sqlGetLastUser = "SELECT MAX(id) FROM logins WHERE username=? and password=?";
	
	public final static String sqlInsertUser = "insert into logins(username, password, user_type) values(?, ?, ?)";
	public final static String sqlInsertOwner = "insert into owners(first_name, last_name, gender, birthday, email, rentals) values(?, ?, ?, ?, ?, ?)";
	public final static String sqlInsertCustomer = "insert into customers(first_name, last_name, gender, birthday, email, rentals) values(?, ?, ?, ?, ?, ?)";
}
