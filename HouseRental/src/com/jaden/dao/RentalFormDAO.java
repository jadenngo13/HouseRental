package com.jaden.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jaden.connection.DBConnection;
import com.jaden.data.RentalForm;
import com.jaden.queries.SqlQueries;

public class RentalFormDAO {
	private PreparedStatement stmt;
	private ResultSet rs;

	public RentalForm getRentalForm(int id) {
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetRentalForm);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) 
				return new RentalForm(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void createRentalForm(RentalForm form) {
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlInsertRentalForm);
			stmt.setInt(1, form.getRentalID());
			stmt.setInt(2, form.getCustomerID());
			stmt.setInt(3, form.getOwnerID());
			stmt.setString(4, form.getStartDate());
			stmt.setString(5, form.getEndDate());
			stmt.setInt(6, 500);
			stmt.execute();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	// Overload
	public void createRentalForm(int rentalID, int custID, int ownerID, String startDate, String endDate) {
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlInsertRentalForm);
			stmt.setInt(1, rentalID);
			stmt.setInt(2, custID);
			stmt.setInt(3, ownerID);
			stmt.setString(4, startDate);
			stmt.setString(5, endDate);
			stmt.setInt(6, 500);
			stmt.execute();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public String getCustomerFirstName(int id) {
		int custID = 0;
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetRentalFormCustomerID);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				custID = rs.getInt(1);
			}
			
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetCustomerFName);
			stmt.setInt(1, custID);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getCustomerLastName(int id) {
		int custID = 0;
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetRentalFormCustomerID);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				custID = rs.getInt(1);
			}
			
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetCustomerLName);
			stmt.setInt(1, custID);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getStartDate(int id) {
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetRentalFormStartDate);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) 
				return rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getEndDate(int id) {
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetRentalFormEndDate);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) 
				return rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int getPrice(int id) {
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetRentalFormPrice);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) 
				return rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
