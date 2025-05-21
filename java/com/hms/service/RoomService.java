package com.hms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hms.config.DbConfig;
import com.hms.model.RoomModel;

public class RoomService {
    
    private Connection dbConn;
    private boolean isConnectionError = false;

    /**
     * Constructor that initializes the database connection. Sets the connection
     * error flag if the connection fails.
     */
    public RoomService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            // Log and handle exceptions related to database connection
            ex.printStackTrace();
            isConnectionError = true;
        }
    }
    
    // get all room and store it in a list
    public List<RoomModel> getAllRoomsInfo() {
        if (isConnectionError) {
            System.out.println("Connection Error!");
            return null;
        }

        // SQL query to fetch room details
        String query = "SELECT room_id, room_no, room_type, room_description, price_per_day, status FROM room";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            ResultSet result = stmt.executeQuery();
            List<RoomModel> roomList = new ArrayList<>();

            while (result.next()) {
                // Create and add RoomModel to the list
                roomList.add(new RoomModel(
                        result.getInt("room_id"), 
                        result.getInt("room_no"), 
                        result.getString("room_type"), 
                        result.getString("room_description"), 
                        result.getFloat("price_per_day"),
                        result.getString("status")
                ));
            }

            return roomList;
        } catch (SQLException e) {
            // Log and handle exceptions related to query execution
            e.printStackTrace();
            return null;
        }
    }
    
    // get room by id 
    public RoomModel getRoomById(int roomId) {
    	
    	if (isConnectionError) {
            System.out.println("Connection Error!");
            return null;
        }
        RoomModel room = null;
        String query = "SELECT * FROM room WHERE room_id = ?";
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            
            stmt.setInt(1, roomId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                room = new RoomModel();
                room.setRoomId(rs.getInt("room_id"));
                room.setRoomNo(rs.getInt("room_no"));
                room.setRoomType(rs.getString("room_type"));
                room.setRoomDescription(rs.getString("room_description"));
                room.setPricePerDay(rs.getFloat("price_per_day"));
                room.setStatus(rs.getString("status"));
                room.setRoomPhoto(rs.getString("room_photo")); // Adjust field names if needed
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return room;
    }
    
    // delete room by its id
    public Boolean deleteRoomById(int roomId) {
    	if (isConnectionError) {
            System.out.println("Connection Error!");
            return null;
        }
        String sql = "DELETE FROM room WHERE room_id = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(sql)) {
            stmt.setInt(1, roomId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // update room status 
    public boolean updateRoomStatus(int roomId, String newStatus) {
        String query = "UPDATE room SET status = ? WHERE room_id = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, newStatus);
            stmt.setInt(2, roomId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}

