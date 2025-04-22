package com.hms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.hms.config.DbConfig;
import com.hms.model.UserModel;

public class RegisterService {
	
	private Connection dbConn;

	/**
	 * Constructor initializes the database connection.
	 */
	public RegisterService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * Registers a new student in the database.
	 *
	 * @param studentModel the student details to be registered
	 * @return Boolean indicating the success of the operation
	 */
	public Boolean addUser(UserModel userModel) {
	    if (dbConn == null) {
	        System.err.println("Database connection is not available.");
	        return null;
	    }

	    String insertQuery = "INSERT INTO user (full_name, email, phone_number, gender, password, role) "
	            + "VALUES (?, ?, ?, ?, ?, ?)"; // Notice the correct number of placeholders

	    try {
	        PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery);
	        
	        insertStmt.setString(1, userModel.getFullName());
	        insertStmt.setString(2, userModel.getEmail());
	        insertStmt.setString(3, userModel.getPhoneNumber());
	        insertStmt.setString(4, userModel.getGender());
	        insertStmt.setString(5, userModel.getPassword());
	        insertStmt.setString(6, "USER");
	  
	        // Execute the update and return true if successful
	        return insertStmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        System.err.println("Error during student registration: " + e.getMessage());
	        e.printStackTrace();
	        return false; // Return false if an error occurs
	    }
	}

}
