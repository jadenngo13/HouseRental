package com.jaden.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.jaden.connection.DBConnection;
import com.jaden.data.Rental;
import com.jaden.data.User;
import com.jaden.sql.SqlQueries;

public class CustomerDAO {
	private PreparedStatement stmt;
	private ResultSet rs;
	private RentalDao rentalDAO;
	
	public User getCustomer(int id) {
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetCustomerFromID);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next())
				return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), "customers", null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Rental> getRented(int id) throws SQLException {
		List<Rental> result = null;
		List<Integer> ids = null;
		
		// Get ids of all rentals
		stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetCustomerRentals);
		stmt.setInt(1, id);
		rs = stmt.executeQuery();
		if (rs.next() && rs.getString(1) != null) {
			ids = Arrays.stream(rs.getString(1).split(","))
			        .map(Integer::parseInt)
			        .collect(Collectors.toList());
		}
		
		// Get all rental objects
		rentalDAO = new RentalDao();
		result = rentalDAO.selectAllRented(ids);
		
		return result;
	}
	
	public List<Rental> getCustomerRentals(int id) {
		List<Rental> result = new ArrayList<>();
		
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetRentalJoinRentalForm);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Rental temp = new Rental(rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9));
				temp.setDaysMsg(getDaysLeftString(rs.getString(2), rs.getString(3)));
				result.add(temp);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void updateRentals(User user) {
		int rentalToDel = -1;
		int rentalFormToDel = -1;
		boolean expired = false;
		
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetRentalFormFromCustID);
			stmt.setInt(1, user.getId());
			rs = stmt.executeQuery();
			while (rs.next()) {
				expired = isRentalExpired(rs.getString(6));
				if (expired) {
					rentalFormToDel = rs.getInt(1);
					rentalToDel = rs.getInt(2);
				}
			}
			
			if (expired) {
				stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlDeleteRentalForm);
				stmt.setInt(1, rentalFormToDel);
				stmt.execute();
				
				stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlDeleteRental);
				stmt.setInt(1, rentalToDel);
				stmt.execute();
				
				int idx = 0;
				StringBuilder sb = new StringBuilder(user.getRentals());
				for (int i = 0; i < sb.length(); i++) {
					if (Character.getNumericValue(sb.charAt(i)) == rentalToDel) {
						idx = i;
					}
				}
				sb.deleteCharAt(idx);
				
				stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlUpdateCustomerRentals);
				stmt.setString(1, sb.toString());
				stmt.setInt(2, user.getId());
				rs = stmt.executeQuery();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getDaysLeftString(String startDate, String endDate) {
		if (isRented(startDate, endDate)) {
			LocalDate s = LocalDate.parse(startDate);
			LocalDate e = LocalDate.parse(endDate);
			Period period = Period.between(s,e);
			int numDays = period.getDays();
			return numDays + " Days Left";
		} else {
			LocalDate tdy = LocalDate.now();
			LocalDate s = LocalDate.parse(startDate);
			Period period = Period.between(tdy,s);
			int numDays = period.getDays();
			return numDays + " Days Until Rental";
		}
	}
	
	public boolean isRentalExpired(String endDate) {
		LocalDate tdy = LocalDate.now();
		return tdy.isAfter(LocalDate.parse(endDate));
	}
	
	public boolean isRented(String startDate, String endDate) {
		LocalDate tdy = LocalDate.now();
		if (tdy.isBefore(LocalDate.parse(endDate)) && tdy.isAfter(LocalDate.parse(startDate))) {
			return true;
		}
		return false;
	}
	
	public String getRentedString(int id) throws SQLException {
		String result = null;
		stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetCustomerRentals);
		stmt.setInt(1, id);
		rs = stmt.executeQuery();
		if (rs.next()) {
			result = rs.getString(1);
		}
		return result;
	}
}
