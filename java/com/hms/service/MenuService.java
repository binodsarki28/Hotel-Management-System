package com.hms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hms.config.DbConfig;
import com.hms.model.MenuModel;

public class MenuService {
	
	private Connection dbConn;
    private boolean isConnectionError = false;

    /**
     * Constructor that initializes the database connection. Sets the connection
     * error flag if the connection fails.
     */
    public  MenuService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            // Log and handle exceptions related to database connection
            ex.printStackTrace();
            isConnectionError = true;
        }
    }
    
    public List<MenuModel> getAllMenuInfo() {
        if (isConnectionError) {
            System.out.println("Connection Error!");
            return null;
        }

        // SQL query to fetch room details
        String query = "SELECT menu_id, food_name, category, food_description, menu_price FROM menu";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            ResultSet result = stmt.executeQuery();
            List<MenuModel> menuList = new ArrayList<>();

            while (result.next()) {
                // Create and add RoomModel to the list
                menuList.add(new MenuModel(
                        result.getInt("menu_id"), 
                        result.getString("food_name"),
                        result.getString("category"),
                        result.getString("food_description"),
                        result.getFloat("menu_price")
                		));            
            }

            return menuList;
        } catch (SQLException e) {
            // Log and handle exceptions related to query execution
            e.printStackTrace();
            return null;
        }
    }
    
    // get menu by its id 
    public MenuModel getMenuById(int id) {
        String query = "SELECT menu_id, food_name, category, food_description, menu_price, menu_photo FROM menu WHERE menu_id = ?";
        try (
            Connection conn = DbConfig.getDbConnection();
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new MenuModel(
                    rs.getInt("menu_id"),
                    rs.getString("food_name"),
                    rs.getString("category"),
                    rs.getString("food_description"),
                    rs.getFloat("menu_price"),
                    rs.getString("menu_photo")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // delete menu by its id
    public Boolean deleteMenuById(int menuId) {
    	if (isConnectionError) {
            System.out.println("Connection Error!");
            return null;
        }
        String sql = "DELETE FROM menu WHERE menu_id = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(sql)) {
            stmt.setInt(1, menuId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
