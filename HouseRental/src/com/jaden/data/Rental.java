package com.jaden.data;

public class Rental {
	private int id;
	private int price;
	private String location;
	private String description;
	private String rentedDates;
	private String imageFName;
	
	public Rental(int price, String location, String description, String rentedDates, String imageFName) {
		this.price = price;
		this.location = location;
		this.description = description;
		this.rentedDates = rentedDates;
		this.imageFName = imageFName;
	}
	
	// Overload
	public Rental(int id, int price, String location, String description, String rentedDates, String imageFName) {
		this.id = id;
		this.price = price;
		this.location = location;
		this.description = description;
		this.rentedDates = rentedDates;
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
	
	public String getRentedDates() {
		return rentedDates;
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
	
	public void setRentedDates(String rentedDates) {
		this.rentedDates = rentedDates;
	}
	
	public void setImageFName(String imageFName) {
		this.imageFName = imageFName;
	}
}
