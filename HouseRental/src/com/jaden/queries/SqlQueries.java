package com.jaden.queries;

public class SqlQueries {
	
	/***** Gets *****/
	public final static String sqlGetUser = "SELECT * FROM logins WHERE username=? and password=?";
	public final static String sqlGetAllUsers = "SELECT * FROM logins";
	public final static String sqlGetAllOwners = "SELECT * FROM owners";
	public final static String sqlGetAllCustomers = "SELECT * FROM customers";
	public final static String sqlGetAllRentals = "SELECT * FROM rentals";
	
	public final static String sqlGetType = "SELECT user_type FROM logins WHERE username=? and password=?";
	
	public final static String sqlGetUserID = "SELECT id FROM logins WHERE username=? and password=?";
	
	public final static String sqlGetAdminFName = "SELECT first_name FROM admins WHERE id=?";
	public final static String sqlGetOwnerFName = "SELECT last_name FROM owners WHERE id=?";
	public final static String sqlGetCustomerFName = "SELECT first_name FROM customers WHERE id=?";
	public final static String sqlGetAdminLName = "SELECT last_name FROM admins WHERE id=?";
	public final static String sqlGetOwnerLName = "SELECT last_name FROM owners WHERE id=?";
	public final static String sqlGetCustomerLName = "SELECT last_name FROM customers WHERE id=?";
	
	public final static String sqlGetOwnerEmail = "SELECT email FROM owners WHERE id=?";
	public final static String sqlGetCustomerEmail = "SELECT email FROM customers WHERE id=?";
	
	public final static String sqlGetOwnerBirthday = "SELECT birthday FROM owners WHERE id=?";
	public final static String sqlGetCustomerBirthday = "SELECT birthday FROM customers WHERE id=?";
	
	public final static String sqlGetRecentOwnerID = "SELECT * FROM owners WHERE id = (SELECT MAX(id) FROM owners)";
	public final static String sqlGetRecentCustomerID = "SELECT * FROM customers WHERE id = (SELECT MAX(id) FROM customers)";
	
	public final static String sqlGetRentalPrice = "SELECT price FROM rentals WHERE id=?";
	public final static String sqlGetRentalLocation = "SELECT location FROM rentals WHERE id=?";
	public final static String sqlGetRentalDescription = "SELECT description FROM rentals WHERE id=?";
	public final static String sqlGetRentalStartDate = "SELECT rent_start_date FROM rentals WHERE id=?";
	public final static String sqlGetRentalEndDate = "SELECT rent_end_date FROM rentals WHERE id=?";
	public final static String sqlGetRentalFileName = "SELECT image_file_name FROM rentals WHERE id=?";

	/***** Inserts *****/
	public final static String sqlInsertUser = "INSERT INTO logins(username, password, user_type) VALUES(?, ?, ?)";
	public final static String sqlInsertOwner = "INSERT INTO owners(first_name, last_name, birthday, email, rentals) VALUES(?, ?, ?, ?, ?)";
	public final static String sqlInsertCustomer = "INSERT INTO customers(first_name, last_name, birthday, email, rentals) VALUES(?, ?, ?, ?, ?)";
	
	/***** Updates *****/
	public static String sqlUpdateUser = "UPDATE logins SET username=? WHERE id=? AND user_type=?";
	public static String sqlUpdateOwner= "UPDATE owners SET first_name=?, last_name=?, birthday=?, email=? WHERE id=?";
	public static String sqlUpdateCustomer= "UPDATE customers SET first_name=?, last_name=?, birthday=?, email=? WHERE id=?";
	
	public static String sqlUpdateCustomerRentals = "UPDATE customers SET rentals=? WHERE id=?";
	public static String sqlUpdateRental = "UPDATE rentals SET rent_start_date=?, rent_end_date=? WHERE id=?";
	
}
