package com.hms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hms.config.DbConfig;
import com.hms.model.RoomModel;

public class AddRoomService {
	
	private Connection dbConn;

	/**
	 * Constructor initializes the database connection.
	 */
	public AddRoomService() {
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
	public Boolean addRoom(RoomModel roomModel) {
	    if (dbConn == null) {
	        System.err.println("Database connection is not available.");
	        return null;
	    }

	    String insertQuery = "INSERT INTO room (room_no, room_type, room_description, price_per_day, status, room_photo) "
	            + "VALUES (?, ?, ?, ?, ?, ?)"; 

	    try {
	        PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery);
	        
	        insertStmt.setInt(1, roomModel.getRoomNo());
	        insertStmt.setString(2, roomModel.getRoomType());
	        insertStmt.setString(3, roomModel.getRoomDescription());
	        insertStmt.setFloat(4, roomModel.getPricePerDay());
	        insertStmt.setString(5, roomModel.getStatus());
	        insertStmt.setString(6, roomModel.getRoomPhoto());
	  
	        // Execute the update and return true if successful
	        return insertStmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        System.err.println("Error during adding Room: " + e.getMessage());
	        e.printStackTrace();
	        return false; // Return false if an error occurs
	    }
	}
	
	/**
	 * Fetches all rooms from the database.
	 */
	public List<RoomModel> getAllRooms() {
	    List<RoomModel> rooms = new ArrayList<>();

	    String query = "SELECT * FROM room";

	    try (PreparedStatement stmt = dbConn.prepareStatement(query);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            RoomModel room = new RoomModel(
	                rs.getInt("room_no"),
	                rs.getString("room_type"),
	                rs.getString("room_description"),
	                rs.getFloat("price_per_day"),
	                rs.getString("status"),
	                rs.getString("room_photo")
	            );
	            rooms.add(room);
	        }

	    } catch (SQLException e) {
	        System.err.println("Error fetching room list: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return rooms;
	}

}
