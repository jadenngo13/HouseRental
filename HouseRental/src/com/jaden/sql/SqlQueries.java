package com.jaden.sql;

public class SqlQueries {
	
	/***** Gets *****/
	public final static String sqlGetUserFromLogin = "SELECT * FROM logins WHERE username=? and password=?";
	public final static String sqlGetUser = "SELECT * FROM logins WHERE id=? and user_type=?";
	public final static String sqlGetAllUsers = "SELECT * FROM logins";
	public final static String sqlGetAllAdmins = "SELECT * FROM admins";
	public final static String sqlGetAllOwners = "SELECT * FROM owners";
	public final static String sqlGetAllCustomers = "SELECT * FROM customers";
	public final static String sqlGetAllRentals = "SELECT * FROM rentals";
	public final static String sqlGetOwnerFromID = "SELECT * FROM owners WHERE id=?";
	public final static String sqlGetCustomerFromID = "SELECT * FROM customers WHERE id=?";
	public final static String sqlGetRentalFromID = "SELECT * FROM rentals WHERE id=?";
	
	public final static String sqlGetRentalForm = "SELECT * FROM rental_forms WHERE id=?";
	public final static String sqlGetRentalFormFromCustID = "SELECT * FROM rental_forms WHERE customer_id=?";
	public final static String sqlGetRentalFormFromOwnerID = "SELECT * FROM rental_forms WHERE owner_id=?";
	public final static String sqlGetRentalFormFromRentalID = "SELECT * FROM rental_forms WHERE rental_id=?";
	
	public final static String sqlGetRentalFormCustomerID = "SELECT customer_id FROM rental_forms WHERE rental_id=?";
	public final static String sqlGetRentalFormPrice = "SELECT price FROM rental_forms WHERE id=?";
	public final static String sqlGetRentalFormStartDate = "SELECT start_date FROM rental_forms WHERE rental_id=?";
	public final static String sqlGetRentalFormEndDate = "SELECT end_date FROM rental_forms WHERE rental_id=?";
	
	public final static String sqlGetType = "SELECT user_type FROM logins WHERE username=? and password=?";
	
	public final static String sqlGetUserID = "SELECT id FROM logins WHERE username=? and password=?";
	
	public final static String sqlGetAdminFName = "SELECT first_name FROM admins WHERE id=?";
	public final static String sqlGetOwnerFName = "SELECT first_name FROM owners WHERE id=?";
	public final static String sqlGetCustomerFName = "SELECT first_name FROM customers WHERE id=?";
	public final static String sqlGetAdminLName = "SELECT last_name FROM admins WHERE id=?";
	public final static String sqlGetOwnerLName = "SELECT last_name FROM owners WHERE id=?";
	public final static String sqlGetCustomerLName = "SELECT last_name FROM customers WHERE id=?";
	
	public final static String sqlGetAdminEmail = "SELECT email FROM admins WHERE id=?";
	public final static String sqlGetOwnerEmail = "SELECT email FROM owners WHERE id=?";
	public final static String sqlGetCustomerEmail = "SELECT email FROM customers WHERE id=?";
	
	public final static String sqlGetAdminBirthday = "SELECT birthday FROM admins WHERE id=?";
	public final static String sqlGetOwnerBirthday = "SELECT birthday FROM owners WHERE id=?";
	public final static String sqlGetCustomerBirthday = "SELECT birthday FROM customers WHERE id=?";
	
	public final static String sqlGetUserUsername = "SELECT username FROM logins WHERE id=? AND user_type=?";
	public final static String sqlGetUserPassword = "SELECT password FROM logins WHERE id=? AND user_type=?";
	
	public final static String sqlGetOwnerRentals = "SELECT rentals FROM owners WHERE id=?";
	public final static String sqlGetCustomerRentals = "SELECT rentals FROM customers WHERE id=?";
	
	public final static String sqlGetRecentOwnerID = "SELECT * FROM owners WHERE id = (SELECT MAX(id) FROM owners)";
	public final static String sqlGetRecentCustomerID = "SELECT * FROM customers WHERE id = (SELECT MAX(id) FROM customers)";
	
	public final static String sqlGetRentalOwnerID = "SELECT owner_id FROM rentals WHERE id=?";
	public final static String sqlGetRentalPrice = "SELECT price FROM rentals WHERE id=?";
	public final static String sqlGetRentalLocation = "SELECT location FROM rentals WHERE id=?";
	public final static String sqlGetRentalDescription = "SELECT description FROM rentals WHERE id=?";
	public final static String sqlGetRentalFileName = "SELECT image_file_name FROM rentals WHERE id=?";
	
	public final static String sqlGetRentalJoinRentalForm = "SELECT rental_forms.rental_id, rental_forms.customer_id, rental_forms.start_date, rental_forms.end_date, "
			+ "rentals.id, rentals.owner_id, rentals.price, rentals.location, rentals.description, rentals.image_file_name "
			+ "FROM rental_forms "
			+ "INNER JOIN rentals ON rental_forms.rental_id = rentals.id";
	
	public static String sqlGetRecentAdmin = "SELECT * FROM admins WHERE id = (SELECT MAX(id) FROM admins);";
	public static String sqlGetRecentOwner = "SELECT * FROM owners WHERE id = (SELECT MAX(id) FROM owners);";
	public static String sqlGetRecentCustomer = "SELECT * FROM customers WHERE id = (SELECT MAX(id) FROM customers);";
	public static String sqlGetRecentRental  = "SELECT * FROM rentals WHERE id = (SELECT MAX(id) FROM rentals);";

	/***** Inserts *****/
	public final static String sqlInsertUser = "INSERT INTO logins(id, username, password, user_type) VALUES(?, ?, ?, ?)";
	public final static String sqlInsertAdmin = "INSERT INTO admins(first_name, last_name, birthday, email, rentals) VALUES(?, ?, ?, ?, ?)";
	public final static String sqlInsertOwner = "INSERT INTO owners(first_name, last_name, birthday, email, rentals) VALUES(?, ?, ?, ?, ?)";
	public final static String sqlInsertCustomer = "INSERT INTO customers(first_name, last_name, birthday, email, rentals) VALUES(?, ?, ?, ?, ?)";
	public final static String sqlInsertRental = "INSERT INTO rentals(owner_id, price, location, description, image_file_name) VALUES(?, ?, ?, ?, ?)";
	public final static String sqlInsertRentalForm = "INSERT INTO rental_forms(rental_id, customer_id, owner_id, start_date, end_date, price_total) VALUES(?, ?, ?, ?, ?, ?)";

	
	/***** Updates *****/
	public static String sqlUpdateUser = "UPDATE logins SET username=? WHERE id=? AND user_type=?";
	public static String sqlUpdateAdmin= "UPDATE admins SET first_name=?, last_name=?, birthday=?, email=? WHERE id=?";
	public static String sqlUpdateOwner= "UPDATE owners SET first_name=?, last_name=?, birthday=?, email=? WHERE id=?";
	public static String sqlUpdateCustomer= "UPDATE customers SET first_name=?, last_name=?, birthday=?, email=? WHERE id=?";
	public static String sqlUpdateRental= "UPDATE rentals SET price=?, location=?, description=?, image_file_name=? WHERE id=?";
	
	public static String sqlUpdateCustomerRentals = "UPDATE customers SET rentals=? WHERE id=?"; 
	public static String sqlUpdateOwnerRentals = "UPDATE owners SET rentals=? WHERE id=?"; 
	
	
	/***** Deletions *****/
	public static String sqlDeleteUser = "DELETE FROM logins WHERE id=? AND user_type=?";
	public static String sqlDeleteOwner = "DELETE FROM owners WHERE id=?";
	public static String sqlDeleteCustomer = "DELETE FROM customers WHERE id=?";
	public static String sqlDeleteRental = "DELETE FROM rentals WHERE id=?";
	public static String sqlDeleteRentalForm = "DELETE FROM rental_forms WHERE id=?";
	
	public static String sqlDeleteRentalFormFromRentalID = "DELETE FROM rental_forms WHERE rental_id=?";
}
