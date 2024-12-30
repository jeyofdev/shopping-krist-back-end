package com.jeyofdev.shopping_krist.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DatabaseInitializer {

    public static void initializeDatabase(String jdbcUrl, String user, String password, String dbName) {
        String createDbQuery = "CREATE DATABASE " + dbName;
        try (Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
             Statement statement = connection.createStatement()) {

            // If the database does not exist, create it
            statement.executeUpdate(createDbQuery);
            System.out.println("Database created successfully!");
        } catch (SQLException e) {
            if (e.getSQLState().equals("42P04")) { // Code error for "Database already exists"
                System.out.println("The database already exists.");
            } else {
                System.err.println("Error creating database : " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
}
