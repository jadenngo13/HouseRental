package com.jaden.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jaden.connection.DBConnection;
import com.jaden.data.Rental;
import com.jaden.queries.SqlQueries;

public class RentalDao {
	PreparedStatement stmt;
	ResultSet rs;

	public List < Rental > selectAllRentals() {

        List < Rental > rentals = new ArrayList < > ();
        try {
            stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetAllRentals);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int price = rs.getInt("price");
                String location = rs.getString("location");
                String desc = rs.getString("description");
                String rentStartDate = rs.getString("rent_start_date");
                String rentEndDate = rs.getString("rent_end_date");
                String imageFName = rs.getString("image_file_name");
                rentals.add(new Rental(id, price, location, desc, rentStartDate, rentEndDate, imageFName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentals;
    }
	
	public int getPrice(int id) {
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetRentalPrice);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next())
				return rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
 	
	public String getLocation(int id) {
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetRentalLocation);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next())
				return rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
 	
	public String getDescription(int id) {
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetRentalDescription);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next())
				return rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String getRentStartDate(int id) {
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetRentalStartDate);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next())
				return rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String getRentEndDate(int id) {
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetRentalEndDate);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next())
				return rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
 	
	public String getImageFile(int id) {
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetRentalFileName);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next())
				return rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public void rentRental(int rentalID, String rentStart, String rentEnd, int custId) {
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlUpdateRental);
			stmt.setString(1, rentStart);
			stmt.setString(2, rentEnd);
			stmt.setInt(3, rentalID);
			stmt.execute();
			
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlUpdateCustomerRentals);
			stmt.setInt(1, rentalID);
			stmt.setInt(2, custId);
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}





