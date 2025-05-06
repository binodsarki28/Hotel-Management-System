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
    
    public List<RoomModel> getAllRoomsInfo() {
        if (isConnectionError) {
            System.out.println("Connection Error!");
            return null;
        }

        // SQL query to fetch room details
        String query = "SELECT room_id, room_no, room_type, room_description, price_per_day FROM room";
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
                        result.getFloat("price_per_day")
                ));
            }

            return roomList;
        } catch (SQLException e) {
            // Log and handle exceptions related to query execution
            e.printStackTrace();
            return null;
        }
    }
}

