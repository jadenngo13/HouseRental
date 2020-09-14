package com.jaden.data;

public class RentalForm {
	private int id;
	private int rentalID;
	private int customerID;
	private int ownerID;
	private String startDate;
	private String endDate;
	private int price;
	
	public RentalForm(int id, int rentalID, int customerID, int ownerID, String startDate, String endDate, int price) {
		this.id = id;
		this.rentalID = rentalID;
		this.customerID = customerID;
		this.ownerID = ownerID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
	}
	
	// Overload
	public RentalForm(int rentalID, int customerID, int ownerID, String startDate, String endDate, int price) {
		this.rentalID = rentalID;
		this.customerID = customerID;
		this.ownerID = ownerID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
	public int getRentalID() {
		return rentalID;
	}
	public int getCustomerID() {
		return customerID;
	}
	public int getOwnerID() {
		return ownerID;
	}
	public String getStartDate() {
		return startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public int getPrice() {
		return price;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setRentalID(int rentalID) {
		this.rentalID = rentalID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
