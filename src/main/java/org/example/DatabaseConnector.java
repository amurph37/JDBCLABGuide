package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {

    // Method to establish a connection to the SQLite database
    public Connection connect() {
        Connection connection = null;
        try {
            // Connect to your local SQLite database
            String url = "jdbc:sqlite:C:\\Users\\admin\\classfiles\\FS104\\BookStore (SQL)\\database.db";

            // Establishing a connection to the SQLite database
            connection = DriverManager.getConnection(url);
            System.out.println("Successfully connected to the database!");
        } catch (SQLException e) {
            // Handling SQLException if a connection cannot be established
            System.out.println("Error connecting to the database.");
            e.printStackTrace();
        }
        return connection;
    }

    // Method to retrieve all records from the 'books' table and print the results
    public void selectAllBooks() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Establishing a connection to the SQLite database
            connection = connect();

            if (connection != null) {
                // Create a Statement object
                statement = connection.createStatement();

                // SQL SELECT query to retrieve all records from the 'books' table
                String sql = "SELECT * FROM Books";

                // Execute the query and store the result in a ResultSet object
                resultSet = statement.executeQuery(sql);

                // Loop through the result set and print the records
                while (resultSet.next()) {
                    int id = resultSet.getInt("BookID");
                    String title = resultSet.getString("Title");
                    String author = resultSet.getString("Authors");
                    double price = resultSet.getDouble("Price");

                    System.out.println("ID: " + id + ", Title: " + title + ", Author: " + author + ", Price: $" + price);
                }
            }
        } catch (SQLException e) {
            // Handling SQL exception if something goes wrong
            System.out.println("Error executing the SELECT query.");
            e.printStackTrace();
        } finally {
            // Cleanup: Closing the ResultSet, Statement, and Connection objects
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.out.println("Error closing resources.");
                e.printStackTrace();
            }
        }
    }
}
