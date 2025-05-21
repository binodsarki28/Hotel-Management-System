package com.hms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hms.config.DbConfig;
import com.hms.model.BookingModel;

public class BookingService {
	
	private Connection dbConn;

	/**
	 * Constructor initializes the database connection.
	 */
	public BookingService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	public List<BookingModel> getAllBookings() {
	    List<BookingModel> bookings = new ArrayList<>();

	    String query = "SELECT * FROM booking";

	    try (PreparedStatement stmt = dbConn.prepareStatement(query);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            BookingModel booking = new BookingModel(
	            	rs.getInt("booking_id"),
	                rs.getDate("check_in_date"),
	                rs.getDate("check_out_date"),
	                rs.getInt("no_of_guest"),
	                rs.getFloat("total_amount"),
	                rs.getString("status"),
	                rs.getInt("user_id"),
	                rs.getInt("room_id")
	            );
	            bookings.add(booking);
	        }

	    } catch (SQLException e) {
	        System.err.println("Error fetching room list: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return bookings;
	}
	
	public Boolean addBooking(BookingModel bookingModel) {
		 if (dbConn == null) {
		        System.err.println("Database connection is not available.");
		        return null;
		    }
		 
		 String insertQuery = "INSERT INTO booking (check_in_date, check_out_date, no_of_guest, total_amount, status, user_id, room_id) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?)";

		    try {
		        PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery);
		        
		        insertStmt.setDate(1, bookingModel.getCheckInDate());
		        insertStmt.setDate(2, bookingModel.getCheckOutDate());
		        insertStmt.setInt(3, bookingModel.getNoOfGuest());
		        insertStmt.setFloat(4, bookingModel.getTotalAmount());
		        insertStmt.setString(5, "Booked");
		        insertStmt.setInt(6, bookingModel.getUserId());
		        insertStmt.setInt(7, bookingModel.getRoomId());
		  
		        // Execute the update and return true if successful
		        return insertStmt.executeUpdate() > 0;
		    } catch (SQLException e) {
		        System.err.println("Error during student registration: " + e.getMessage());
		        e.printStackTrace();
		        return false; // Return false if an error occurs
		    }
		}
	
	public BookingModel getBookingById(int bookingId) {
        BookingModel booking = null;
        if (dbConn == null) {
	        System.err.println("Database connection is not available.");
	        return null;
	    }

        String query = "SELECT * FROM booking WHERE booking_id = ?";

        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, bookingId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                booking = new BookingModel();
                booking.setBookingId(rs.getInt("booking_id"));
                booking.setCheckInDate(rs.getDate("check_in_date"));
                booking.setCheckOutDate(rs.getDate("check_out_date"));
                booking.setNoOfGuest(rs.getInt("no_of_guest"));
                booking.setTotalAmount(rs.getFloat("total_amount"));
                booking.setStatus(rs.getString("status"));
                booking.setUserId(rs.getInt("user_id"));
                booking.setRoomId(rs.getInt("room_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return booking;
    }
}
