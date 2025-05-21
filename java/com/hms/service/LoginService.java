package com.hms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hms.config.DbConfig;
import com.hms.model.UserModel;
import com.hms.util.PasswordUtil;

public class LoginService {
	
	private Connection dbConn;
	private boolean isConnectionError = false;

	/**
	 * Constructor initializes the database connection. Sets the connection error
	 * flag if the connection fails.
	 */
	public LoginService() {
		try {
			dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			isConnectionError = true;
		}
	}

	/**
	 * Validates the user credentials against the database records.
	 *
	 * @param studentModel the StudentModel object containing user credentials
	 * @return true if the user credentials are valid, false otherwise; null if a
	 *         connection error occurs
	 */
	public Boolean loginUser(UserModel userModel) {
		if (isConnectionError) {
			System.out.println("Connection Error!");
			return null;
		}

		String query = "SELECT email, password FROM user WHERE email = ?";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			stmt.setString(1, userModel.getEmail());
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				return validatePassword(result, userModel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return false;
	}

	/**
	 * Validates the password retrieved from the database.
	 *
	 * @param result       the ResultSet containing the username and password from
	 *                     the database
	 * @param studentModel the StudentModel object containing user credentials
	 * @return true if the passwords match, false otherwise
	 * @throws SQLException if a database access error occurs
	 */
	private boolean validatePassword(ResultSet result, UserModel userModel) throws SQLException {
		String dbEmail= result.getString("email");
		String dbPassword = result.getString("password");

		return dbEmail.equals(userModel.getEmail())
				&& PasswordUtil.decrypt(dbPassword, dbEmail).equals(userModel.getPassword());
	}
	
	public UserModel getUserDetailsByEmail(String email) {
	    if (isConnectionError) {
	        System.out.println("Connection Error!");
	        return null;
	    }

	    String query = "SELECT * FROM user WHERE email = ?";
	    UserModel user = null;

	    try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
	        stmt.setString(1, email);
	        ResultSet result = stmt.executeQuery();

	        if (result.next()) {
	            user = new UserModel(0, query, query, query, query, query, query, query);
	            user.setUserId(result.getInt("user_id"));
	            user.setFullName(result.getString("full_name"));
	            user.setEmail(result.getString("email"));
	            user.setFullName(result.getString("full_name"));
	            user.setPhoneNumber(result.getString("phone_number"));
	            user.setGender(result.getString("gender"));
	            user.setProfilePhoto(result.getString("profile_photo"));
	            // Add other fields as needed
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return user;
	}

}
