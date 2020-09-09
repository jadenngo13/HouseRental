package com.jaden.data;

public class Rental {
	private int id;
	private int price;
	private String location;
	private String description;
	private String rentStartDate;
	private String rentEndDate;
	private String imageFName;
	
	public Rental(int price, String location, String description, String rentStartDate, String rentEndDate, String imageFName) {
		this.price = price;
		this.location = location;
		this.description = description;
		this.rentStartDate = rentStartDate;
		this.rentEndDate = rentEndDate;
		this.imageFName = imageFName;
	}
	
	// Overload
	public Rental(int id, int price, String location, String description, String rentStartDate, String rentEndDate, String imageFName) {
		this.id = id;
		this.price = price;
		this.location = location;
		this.description = description;
		this.rentStartDate = rentStartDate;
		this.rentEndDate = rentEndDate;
		this.imageFName = imageFName;
	}
	
	public int getId() {
		return id;
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
	
	public String getRentStartDate() {
		return rentStartDate;
	}
	
	public String getRentEndDate() {
		return rentEndDate;
	}
	
	public String getImageFName() {
		return imageFName;
	}
	public void setId(int id) {
		this.id = id;
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
	
	public void setRentStartDate(String rentStartDate) {
		this.rentStartDate = rentStartDate;
	}

	public void setRentEndDate(String rentEndDate) {
		this.rentEndDate = rentEndDate;
	}
	
	public void setImageFName(String imageFName) {
		this.imageFName = imageFName;
	}
	
	
}
