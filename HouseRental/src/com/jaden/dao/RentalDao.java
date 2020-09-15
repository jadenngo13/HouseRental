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
import com.jaden.data.User;
import com.jaden.sql.SqlQueries;

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
	
	public Rental selectRental(int id) {
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetRentalFromID);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next())
				return new Rental(id, rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
	
	public User getOwner(int id) {
		int ownerID = getOwnerID(id);
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetOwnerFromID);
			stmt.setInt(1, ownerID);
			rs = stmt.executeQuery();
			if (rs.next())
				return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), "owners", null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
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
	
	public void insertRental(User owner, Rental newRental) {
		try {
			// Insert rental into db
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlInsertRental);
			stmt.setInt(1, newRental.getOwner());
			stmt.setInt(2, newRental.getPrice());
			stmt.setString(3, newRental.getLocation());
			stmt.setString(4, newRental.getDescription());
			stmt.setString(5, newRental.getImageFName());
			stmt.execute();
			
			// Get newly inserted rentals' id
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetRecentRental);
			rs = stmt.executeQuery();
			if (rs.next())
				newRental.setId(rs.getInt(1));
			
			// Update owner's rentals
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlUpdateOwnerRentals);
			System.out.println("insert into rentals: " + owner.getRentals() + " + " + newRental.getId());
			stmt.setString(1, owner.getRentals() + "," + newRental.getId());
			stmt.setInt(2, owner.getId());
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public boolean updateRental(Rental rental) throws SQLException {
        boolean rentalUpdated = false;
        try {
        	// Update rental
        	stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlUpdateRental);
        	stmt.setInt(1, rental.getPrice());
    		stmt.setString(2, rental.getLocation());
    		stmt.setString(3, rental.getDescription());
    		stmt.setString(4, rental.getImageFName());
    		stmt.setInt(5, rental.getId());
    		stmt.executeUpdate();

            rentalUpdated = stmt.executeUpdate() > 0;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return rentalUpdated;
    }

	public void deleteRental(User owner, Rental toDel) {
		try {
			// Update renters, if currently being rented do not allow deletion
			List<User> currRenters = getRenters(toDel, "current");
			List<User> allRenters = getRenters(toDel, "all");
			if (currRenters.size() <= 0) {
				for (User u : allRenters) {
					
					String[] r = u.getRentals().split(",");
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < r.length; i++) {
						if (!r[i].equals(Integer.toString(toDel.getId())));
							sb.append(r[i] + ',');
					}
					
					stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlUpdateCustomerRentals);
					stmt.setString(1, sb.toString());
					stmt.setInt(2, u.getId());
					rs = stmt.executeQuery();
				}	
			} else {
				System.out.println("Cannot delete rental for it is currently being rented.");
				return;
			}
			System.out.println("we are deleting");
			
			// Delete rental forms
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlDeleteRentalFormFromRentalID);
			stmt.setInt(1, toDel.getId());
			stmt.execute();
			
			// Update owner
			int idx = 0;
			String[] r = owner.getRentals().split(",");
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < r.length; i++) {
				if (!r[i].equals(Integer.toString(toDel.getId())));
					sb.append(r[i] + ',');
			}
			
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlUpdateOwnerRentals);
			stmt.setString(1, sb.toString());
			stmt.setInt(2, owner.getId());
			stmt.execute();
			
			// Delete rental
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlDeleteRental);
			stmt.setInt(1, toDel.getId());
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public boolean isRented(Rental rental) {
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetRentalFormFromRentalID);
			stmt.setInt(1, rental.getId());
			while (rs.next()) {
				LocalDate s = LocalDate.parse(rs.getString("start_date"));
				LocalDate e = LocalDate.parse(rs.getString("end_date"));
				LocalDate tdy = LocalDate.now();
				if (tdy.isBefore(e) && tdy.isAfter(s)) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<User> getRenters(Rental rental, String type) {
		List<User> result = new ArrayList<>();
		List<Integer> ids = new ArrayList<>();
		try {
			stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetRentalFormFromRentalID);
			stmt.setInt(1, rental.getId());
			while (rs.next()) {
				if (type.equals("current")) {
					LocalDate s = LocalDate.parse(rs.getString("start_date"));
					LocalDate e = LocalDate.parse(rs.getString("end_date"));
					LocalDate tdy = LocalDate.now();
					if (tdy.isBefore(e) && tdy.isAfter(s)) {
						ids.add(rs.getInt("customer_id"));
					}
				} else if (type.equals("all")) {
					ids.add(rs.getInt("customer_id"));
				}
			}
			
			for (int id : ids) {
				stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetCustomerFromID);
				stmt.setInt(1, id);
				rs = stmt.executeQuery();
				if (rs.next())
					result.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), "customers", null, null));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}





