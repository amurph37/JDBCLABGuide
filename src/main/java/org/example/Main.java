package org.example;

public class Main {
    // Main method for testing the functionality
    public static void main(String[] args) {
        DatabaseConnector dbConnector = new DatabaseConnector();
        dbConnector.selectAllBooks();
    }
}