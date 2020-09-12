package com.jaden.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.jaden.connection.DBConnection;
import com.jaden.data.DatePair;
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
                String rentedDates = rs.getString("rented_dates");
                String imageFName = rs.getString("image_file_name");
                rentals.add(new Rental(id, price, location, desc, rentedDates, imageFName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentals;
    }
	
	public List<Rental> selectAllRented(List<Integer> rentalIds) {
		List < Rental > rentals = new ArrayList < > ();
	    try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetRentalFromID);
			for (int id : rentalIds) {
				stmt.setInt(1, id);
				rs = stmt.executeQuery();
				if (rs.next()) {
					rentals.add(new Rental(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
				}
			}
		} catch (Exception e) {
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
	
	public List<String> getRentedDates(int id) {
		List<String> result = new ArrayList<>();
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetRentalRentedDates);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				String[] s = rs.getString(1).split("[\\|,]+");
				for (int i = 0; i < s.length; i++) {
					result.add(s[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String getRentedDatesString(int id) {
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetRentalRentedDates);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				String[] datesArr = rs.getString(1).replace("|", ",").split(",");
				
//				Get all dates that are unavailable to rent 
//					based on starting and ending renting dates
				List<LocalDate> listOfDates = new ArrayList<>();
				for (int i = 0; i < datesArr.length; i+=2) {
					listOfDates.addAll(getDatesBetween(datesArr[i], datesArr[i+1]));
				}
				
				// Convert list back to string
				String sb = formatRentedDates(listOfDates);
				System.out.println(sb);
				
				return sb;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
		return null;
	}
	
	public void rentRental(int rentalID, String rented, String rentStart, String rentEnd, int custId) {
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlUpdateRental);
			String s = formatRentedDates(getDatesBetween(rentStart, rentEnd));
			StringBuilder newRentedDates = new StringBuilder(rented);
			newRentedDates.append(s);
			stmt.setString(1, newRentedDates.toString());
			stmt.setInt(2, rentalID);
			stmt.execute();
			
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlUpdateCustomerRentals);
			stmt.setInt(1, rentalID);
			stmt.setInt(2, custId);
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Find first available rental date given string of all occupied dates
	public String getFirstAvailDate(String dates) {
		LocalDate curr = LocalDate.now();
		LocalDate temp;
		String[] datesArr = dates.split(",");
		System.out.println("dates: " + Arrays.toString(datesArr));
		for (int i = 0; i < datesArr.length; i++) {
			temp = LocalDate.parse(datesArr[i]);
			System.out.println("Compare: " + temp.toString() + " v. " + datesArr[i].toString());
			if (curr.toString().equals(datesArr[i])) {
				curr.plusDays(1);
			} else {
				return temp.toString();
			}
		}
		return curr.toString();
	}
	
	// Format list of dates to be inserting into db
	public String formatRentedDates(List<LocalDate> dates) {
		StringBuilder result = new StringBuilder();
		for (LocalDate d : dates) {
			result.append(d.toString() + ",");
		}
		return result.toString();
	}
	
	public List<LocalDate> getDatesBetween(String start, String end) {
		List<LocalDate> listOfDates = new ArrayList<>();
		LocalDate startDate = LocalDate.parse(start);
		LocalDate endDate = LocalDate.parse(end);
		listOfDates.addAll(startDate.datesUntil(endDate.plusDays(1))
                .collect(Collectors.toList()));
		return listOfDates;
	}
	
	// Check for potential renting overlap 
	public boolean checkDateInterfere(String currDates, String rentStart, String rentEnd) {
		String[] datesArr = currDates.replace("|", ",").split(",");

		List<LocalDate> listOfDates = new ArrayList<>();
		List<LocalDate> listOfNewDates = new ArrayList<>();
		for (int i = 0; i < datesArr.length; i+=2) {
			listOfDates.addAll(getDatesBetween(datesArr[i], datesArr[i+1]));
		}
		listOfNewDates.addAll(getDatesBetween(rentStart, rentEnd));
		
		for (LocalDate date : listOfNewDates) {
			if (listOfDates.contains(date)) 
				return false;
		}
		
		return true;
	}
}





