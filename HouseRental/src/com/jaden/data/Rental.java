package com.jaden.data;

public class Rental {
	private int id;
	private int ownerID;
	private int price;
	private String location;
	private String description;
	private String imageFName;
	
	private String daysMsg;
	private String startDate;
	private String endDate;
	private String rentee;
	private int formID;
	
	public Rental(int ownerID, int price, String location, String description, String imageFName) {
		this.ownerID = ownerID;
		this.price = price;
		this.location = location;
		this.description = description;
		this.imageFName = imageFName;
	}
	
	// Overload
	public Rental(int id, int ownerID, int price, String location, String description, String imageFName) {
		this.id = id;
		this.ownerID = ownerID;
		this.price = price;
		this.location = location;
		this.description = description;
		this.imageFName = imageFName;
	}
	
	// Overload
	public Rental(int id, int ownerID, int price, String location, String description, String imageFName, String rentee, String startDate, String endDate, int formID) {
		this.id = id;
		this.ownerID = ownerID;
		this.price = price;
		this.location = location;
		this.description = description;
		this.imageFName = imageFName;
		this.rentee = rentee;
		this.startDate = startDate;
		this.endDate = endDate;
		this.formID = formID;
	}
	
	public int getId() {
		return id;
	}
	public int getOwner() {
		return ownerID;
	}
	public int getPrice() {
		return price;
	}
	public String getLocation() {
		return location;
	}
	public String getDescription() {
		return description;
	}
	public String getImageFName() {
		return imageFName;
	}
	public String getDaysMsg() {
		return daysMsg;
	}
	public String getRentee() {
		return rentee;
	}
	public String getStartDate() {
		return startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public int getFormID() {
		return formID;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setImageFName(String imageFName) {
		this.imageFName = imageFName;
	}
	public void setDaysMsg(String daysMsg) {
		this.daysMsg = daysMsg;
	}
	public void setRentee(String rentee) {
		this.rentee = rentee;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public void setFormID(int formID) {
		this.formID = formID;
	}
}
