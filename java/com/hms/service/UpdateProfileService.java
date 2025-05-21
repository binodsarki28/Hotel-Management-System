package com.hms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.hms.config.DbConfig;
import com.hms.model.UserModel;

public class UpdateProfileService {
	
	private Connection dbConn;

	/**
	 * Constructor initializes the database connection.
	 */
	public UpdateProfileService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	// for updating the user profile
	public boolean updateUserProfile(UserModel user) {
	    String sql = "UPDATE user SET full_name = ?, email = ?, phone_number = ?, gender = ?, password = ?, profile_photo = ? WHERE user_id = ?";

	    try (PreparedStatement pst = dbConn.prepareStatement(sql)) {
	        pst.setString(1, user.getFullName());
	        pst.setString(2, user.getEmail());
	        pst.setString(3, user.getPhoneNumber());
	        pst.setString(4, user.getGender());
	        pst.setString(5, user.getPassword());  // Assume password is already hashed
	        pst.setString(6, user.getProfilePhoto());
	        pst.setInt(7, user.getUserId());

	        int rows = pst.executeUpdate();
	        return rows > 0;
	    } catch (Exception e) {
	        System.err.println("Error updating profile: " + e.getMessage());
	        return false;
	    }
	}

}
