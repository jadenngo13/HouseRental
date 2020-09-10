package com.jaden.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jaden.connection.DBConnection;
import com.jaden.data.User;
import com.jaden.queries.SqlQueries;

public class UserDAO {
	private PreparedStatement stmt, stmt1;

    public void insertUser(User user) throws SQLException {
        try {
        	// Insert into corresponding data type
        	switch (user.getUserType()) {
        	case "owners":
        		stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlInsertOwner);
        		stmt1 = DBConnection.conn.prepareStatement(SqlQueries.sqlGetRecentOwner);
        		break;
        	case "customers":
        		stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlInsertCustomer);
        		stmt1 = DBConnection.conn.prepareStatement(SqlQueries.sqlGetRecentCustomer);
        		break;
        	default:
        		System.out.println("No Valid type given for creation of new user."); 
        		break;
        	}
        	stmt.setString(1, user.getFirstName());
    		stmt.setString(2, user.getLastName());
    		stmt.setString(3, user.getBday());
    		stmt.setString(4, user.getEmail());
    		stmt.setString(5, user.getRentals());
    		stmt.executeUpdate();
        	
    		// Get newly added user's generated id
    		int id = -1;
    		ResultSet rs = stmt1.executeQuery();
    		if (rs.next()) {
    			id = rs.getInt("id");
    		}
    		
    		// Give user login and insert into db
        	stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlInsertUser);
        	stmt.setInt(1, id);
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getUserType());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User selectUser(int id, String type) {
    	ResultSet rs;
        User user = null;
        String fname = null;
        String lname = null;
        String bday = null;
        String email = null;
        String rentals = null;
        String uname = null;
        String pass = null;
        
        try {
        	stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetUser);
            stmt.setInt(1, id);
            stmt.setString(2, type);
            rs = stmt.executeQuery();
            if (rs.next()) {
                uname = rs.getString("username");
                pass = rs.getString("password");
               
            }
            
            stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetCustomerFromID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
            	fname = rs.getString("first_name");
            	lname = rs.getString("last_name");
            	bday = rs.getString("birthday");
            	email = rs.getString("email");
            	rentals = rs.getString("rentals");
            }
            
            System.out.println(id + " " + fname + " " + lname + " " + bday + " " + email + " " + type + " " + rentals + " " + uname + " " + pass);
            user = new User(id, fname, lname, bday, email, type, rentals, uname, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List < User > selectAllUsers() {
        List < User > users = new ArrayList < > ();

        try {
        	stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlGetAllUsers);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String uname = rs.getString("username");
                String pass = rs.getString("password");
                String type = rs.getString("user_type");
                
                users.add(new User(id, uname, pass, type));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean deleteUser(int id, String type) throws SQLException {
    	boolean deletedUser = false;
        try { 
        	// Delete user
        	switch(type) {
        	case "owners":
        		stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlDeleteOwner);
        		break;
        	case "customers":
        		stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlDeleteCustomer);
        		break;
        	}
        	stmt.setInt(1, id);
        	stmt.executeUpdate();
        	
        	// Delete login credentials
        	stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlDeleteUser);
            stmt.setInt(1, id);
            stmt.setString(2, type);
            deletedUser = stmt.executeUpdate() > 0;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return deletedUser;
    }

    public boolean updateUser(User user) throws SQLException {
        boolean userUpdated = false;
        try {
        	// Update user
        	switch(user.getUserType()) {
        	case "owner":
        		stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlUpdateOwner);
        		break;
        	case "customers":
        		stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlUpdateCustomer);
        		break;
        	default:
        		System.out.println("No valid user type for update.");
        		break;
        	}
        	stmt.setString(1, user.getFirstName());
    		stmt.setString(2, user.getLastName());
    		stmt.setString(3, user.getBday());
    		stmt.setString(4, user.getEmail());
    		stmt.setInt(5, user.getId());
    		stmt.executeUpdate();
        	
        	// Update login credentials
        	stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlUpdateUser);
        	stmt.setString(1, user.getUsername());
        	stmt.setString(2, user.getPassword());
        	stmt.setInt(3, user.getId());
        	stmt.setString(4, user.getUserType());
        	
            userUpdated = stmt.executeUpdate() > 0;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return userUpdated;
    }
}
