package com.hms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hms.config.DbConfig;
import com.hms.model.MenuModel;

public class AddMenuService {
	
	private Connection dbConn;

	/**
	 * Constructor initializes the database connection.
	 */
	public AddMenuService() {
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
	public Boolean addMenu(MenuModel menuModel) {
	    if (dbConn == null) {
	        System.err.println("Database connection is not available.");
	        return null;
	    }

	    String insertQuery = "INSERT INTO menu (food_name, category, food_description, menu_price, menu_photo)"
	            + "VALUES (?, ?, ?, ?, ?)"; 

	    try {
	        PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery);
	        
	        insertStmt.setString(1, menuModel.getFoodName());
	        insertStmt.setString(2, menuModel.getCategory());
	        insertStmt.setString(3, menuModel.getFoodDescription());
	        insertStmt.setFloat(4, menuModel.getMenuPrice());
	        insertStmt.setString(5, menuModel.getMenuPhoto());
	      
	  
	        // Execute the update and return true if successful
	        return insertStmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        System.err.println("Error during adding Menu: " + e.getMessage());
	        e.printStackTrace();
	        return false; // Return false if an error occurs
	    }
	}
	
	/**
	 * Fetches all menus from the database.
	 */
	public List<MenuModel> getAllMenus() {
		
	    List<MenuModel> menus = new ArrayList<>();

	    String query = "SELECT * FROM menu";

	    try (PreparedStatement stmt = dbConn.prepareStatement(query);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            MenuModel menu = new MenuModel(
	                rs.getString("food_name"),
	                rs.getString("category"),
	                rs.getString("food_description"),
	                rs.getFloat("menu_price"),
	                rs.getString("menu_photo")
	            );
	            menus.add(menu);
	        }

	    } catch (SQLException e) {
	        System.err.println("Error fetching menu list: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return menus;  // Return the correct list
	}

}