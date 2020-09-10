package com.jaden.data;

public class User {
	private int id;
	private String firstName;
	private String lastName;
	private String bday;
	private String email;
	private String rentals;
	private String userType;
	private String username;
	private String password;
	
	public User(String firstName, String lastName, String bday, String email, String rentals, String userType,
			String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.bday = bday;
		this.email = email;
		this.rentals = rentals;
		this.userType = userType;
		this.username = username;
		this.password = password;
	}
	
	public User(int id, String firstName, String lastName, String bday, String email, String rentals, String userType,
			String username, String password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.bday = bday;
		this.email = email;
		this.rentals = rentals;
		this.userType = userType;
		this.username = username;
		this.password = password;
	}
	
	public User(int id, String username, String password, String userType) {
		this.id = id;
		this.userType = userType;
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getBday() {
		return bday;
	}

	public String getEmail() {
		return email;
	}

	public String getRentals() {
		return rentals;
	}

	public String getUserType() {
		return userType;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setBday(String bday) {
		this.bday = bday;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRentals(String rentals) {
		this.rentals = rentals;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
