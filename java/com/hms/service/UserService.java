package com.hms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hms.config.DbConfig;
import com.hms.model.BookingModel;
import com.hms.model.UserModel;

public class UserService {
	
	private Connection dbConn;
    private boolean isConnectionError = false;

    /**
     * Constructor that initializes the database connection. Sets the connection
     * error flag if the connection fails.
     */
    public UserService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            // Log and handle exceptions related to database connection
            ex.printStackTrace();
            isConnectionError = true;
        }
    }
    
    public List<UserModel> getAllUserInfo() {
        if (isConnectionError) {
            System.out.println("Connection Error!");
            return null;
        }

        // SQL query to fetch room details
        String query = "SELECT user_id, full_name, email, phone_number, gender FROM user WHERE role = 'USER'";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            ResultSet result = stmt.executeQuery();
            List<UserModel> userList = new ArrayList<>();

            while (result.next()) {
                // Create and add RoomModel to the list
                userList.add(new UserModel(
                        result.getInt("user_id"), 
                        result.getString("full_name"),
                        result.getString("email"),
                        result.getString("phone_number"),
                        result.getString("gender")
                ));
            }

            return userList;
        } catch (SQLException e) {
            // Log and handle exceptions related to query execution
            e.printStackTrace();
            return null;
        }
    }
    
    public Boolean deleteUserById(int userId) {
    	if (isConnectionError) {
            System.out.println("Connection Error!");
            return null;
        }
        String sql = "DELETE FROM user WHERE user_id = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<BookingModel> getBookingsByUserId(int userId) {
        List<BookingModel> bookings = new ArrayList<>();

        if (isConnectionError) {
            System.out.println("Connection Error!");
            return bookings; // empty list
        }

        String query = "SELECT * FROM booking WHERE user_id = ?";

        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                BookingModel booking = new BookingModel();
                booking.setBookingId(rs.getInt("booking_id"));
                booking.setCheckInDate(rs.getDate("check_in_date"));
                booking.setCheckOutDate(rs.getDate("check_out_date"));
                booking.setNoOfGuest(rs.getInt("no_of_guest"));
                booking.setTotalAmount(rs.getFloat("total_amount"));
                booking.setStatus(rs.getString("status"));
                booking.setUserId(rs.getInt("user_id"));
                booking.setRoomId(rs.getInt("room_id"));
                bookings.add(booking);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookings;
    }
    
    public boolean updateBookingStatus(int bookingId, String newStatus) {
	    String sql = "UPDATE booking SET status = ? WHERE booking_id = ?";
	    try (PreparedStatement stmt = dbConn.prepareStatement(sql)) {
	        stmt.setString(1, newStatus);
	        stmt.setInt(2, bookingId);
	        int rowsUpdated = stmt.executeUpdate();
	        return rowsUpdated > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
    
    public BookingModel getBookingById(int bookingId) {
        BookingModel booking = null;
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
