package com.jaden.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.jaden.connection.DBConnection;
import com.jaden.data.Rental;
import com.jaden.data.RentalForm;
import com.jaden.data.User;
import com.jaden.sql.SqlQueries;

public class OwnerDAO {
	private PreparedStatement stmt;
	private ResultSet rs;
	private RentalDao rentalDAO;
	
	public User getOwner(int id) {
		try {
			System.out.println("id seraching for: " + id);
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetOwnerFromID);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next())
				return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), "owners", null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("we didnt get one");
		return null;
	}
	
	public List<Rental> getRentals(int id) throws SQLException {
		List<Rental> result =  new ArrayList<>();
		List<Integer> ids =  new ArrayList<>();
		
		// Get ids of all rentals
		stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetOwnerRentals);
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
	
	public List<Rental> getRented(int id) throws SQLException {
		List<Rental> result = new ArrayList<>();
		List<RentalForm> forms =  new ArrayList<>();
		List<Integer> ids =  new ArrayList<>();
		
		// Get ids of all rentals
		stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetRentalFormFromOwnerID);
		stmt.setInt(1, id);
		rs = stmt.executeQuery();
		while (rs.next()) {
			forms.add(new RentalForm(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
			ids.add(rs.getInt(2));
		}
		
		// Get all rental objects and set additional information for display
		rentalDAO = new RentalDao();
		result = rentalDAO.selectAllRented(ids);
		for (Rental r : result) {
			for (RentalForm f : forms) {
				if (r.getId() == f.getRentalID()) {
					stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetCustomerFromID);
					stmt.setInt(1, f.getCustomerID());
					rs = stmt.executeQuery();
					if (rs.next()) {
						r.setRentee(rs.getString(2));
					}
					r.setStartDate(f.getStartDate());
					r.setEndDate(f.getEndDate());
					r.setFormID(f.getId());
					continue;
				}
			}
		}
		
		return result;
	}
	
	public String getRentedString(int id) throws SQLException {
		String result = null;
		stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetOwnerRentals);
		stmt.setInt(1, id);
		rs = stmt.executeQuery();
		if (rs.next()) {
			result = rs.getString(1);
		}
		return result;
	}
}
