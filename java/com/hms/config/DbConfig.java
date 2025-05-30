package com.hms.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// This class set up the connection between the local mysql database and servlet class
public class DbConfig {
	
	// Database configuration information
		private static final String DB_NAME = "hotel-management-system";
		private static final String URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
		private static final String USERNAME = "root";
		private static final String PASSWORD = "WJ28@krhps";

		/**
		 * Establishes a connection to the database.
		 *
		 * @return Connection object for the database
		 * @throws SQLException           if a database access error occurs
		 * @throws ClassNotFoundException if the JDBC driver class is not found
		 */
		public static Connection getDbConnection() throws SQLException, ClassNotFoundException {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}

}
