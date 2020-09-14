package com.jaden.data;

public class Rental {
	private int id;
	private int ownerID;
	private int price;
	private String location;
	private String description;
	private String imageFName;
	
	public String daysMsg;
	public String startDate;
	public String endDate;
	public String rentee;
	public int formID;
	
	public Rental(int price, int ownerID, String location, String description, String imageFName) {
		this.price = price;
		this.ownerID = ownerID;
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
	public String getStartDate() {
		return startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public String getRentee() {
		return rentee;
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
}
