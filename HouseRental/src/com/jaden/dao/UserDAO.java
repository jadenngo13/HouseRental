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
	private PreparedStatement stmt;

    public void insertUser(User user) throws SQLException {
        try {
        	// Insert into corresponding data type
        	switch (user.getUserType()) {
        	case "owners":
        		stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlInsertOwner);
        		break;
        	case "customers":
        		stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlInsertCustomer);
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
        	
    		// Give user login and insert into db
        	stmt = DBConnection.conn.prepareStatement(SqlQueries.sqlInsertUser);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getUserType());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User selectUser(int id) {
        User user = null;
        try {
            stmt.setInt(1, id);
            System.out.println(stmt);
            // Step 3: Execute the query or update query
            ResultSet rs = stmt.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user = new User(id, country, country, country, country, country, country, country, country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List < User > selectAllUsers() {
        List < User > users = new ArrayList < > ();

        try {
        	stmt.setString(SqlQueries.sqlGetAllUsers);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                users.add(new User(id, name, email, country, country, country, country, country, country));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getCountry());
            statement.setInt(4, user.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
