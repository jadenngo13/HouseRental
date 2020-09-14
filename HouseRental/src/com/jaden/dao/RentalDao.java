package com.jaden.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.jaden.connection.DBConnection;
import com.jaden.data.Rental;
import com.jaden.data.RentalForm;
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
                int owner = rs.getInt("owner_id");
                int price = rs.getInt("price");
                String location = rs.getString("location");
                String desc = rs.getString("description");
                String imageFName = rs.getString("image_file_name");
                System.out.println("id: " + id);
                rentals.add(new Rental(id, owner, price, location, desc, imageFName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentals;
    }
	
	public List<Rental> selectAllRented(List<Integer> rentalIDs) {
		List < Rental > rentals = new ArrayList < > ();
		if (rentalIDs == null)
			return rentals;
	    try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetRentalFromID);
			for (int id : rentalIDs) {
				stmt.setInt(1, id);
				rs = stmt.executeQuery();
				if (rs.next()) {
					rentals.add(new Rental(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6)));
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
	
	public int getOwnerID(int id) {
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetRentalOwnerID);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next())
				return rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public List<String> getRentedDates(int id) {
		List<String> result = new ArrayList<>();
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetRentalFormFromRentalID);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			while (rs.next()) {
				result.add(rs.getString(5));
				result.add(rs.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String getAllRentedDatesString(int id) {
		try {
			List<LocalDate> listOfDates = new ArrayList<>();
			List<String> dates = getRentedDates(id);
			for (int i = 0; i < dates.size()-1; i+=2) {
				listOfDates.addAll(getDatesBetween(dates.get(i), dates.get(i+1)));
			}

			// Convert list back to string
			String sb = formatRentedDates(listOfDates, "grab");
			System.out.println(sb);
			
			return sb;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getRentedDatesString(int id) {
		try {
			List<LocalDate> listOfDates = new ArrayList<>();
			List<String> dates = getRentedDates(id);
			for (String d : dates) {
				listOfDates.add(LocalDate.parse(d));
			}
			// Convert list back to string
			String result = formatRentedDates(listOfDates, "grab");
			System.out.println(result);
			
			return result;
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
	
	public void rentRental(int rentalID, String rented, String rentStart, String rentEnd, int custID, String custRentals, int ownerID) {
		try {
			RentalFormDAO rentalFormDAO = new RentalFormDAO();
			RentalForm form = new RentalForm(rentalID, custID, ownerID, rentStart, rentEnd, 500);
			rentalFormDAO.createRentalForm(form);
			
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlUpdateCustomerRentals);
			if (custRentals != null) {
				stmt.setString(1, custRentals + "," + rentalID);
			} else {
				stmt.setInt(1, rentalID);
			}
			stmt.setInt(2, custID);
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Find first available rental date given string of all occupied dates
	public String getFirstAvailDate(String dates) {
		System.out.println("dates: " + dates);
		if (!dates.equals("")) {
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
		} else {
			return "";
		}
	}
	
	// Format list of dates to be grabbed from and inserted into db
	public String formatRentedDates(List<LocalDate> dates, String type) {
		StringBuilder result = new StringBuilder();
		switch(type) {
		case "grab":
			for (LocalDate d : dates) 
				result.append(d.toString() + ",");
			break;
		default:
			System.out.println("Incorrect type given for string format.");
			break;
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





