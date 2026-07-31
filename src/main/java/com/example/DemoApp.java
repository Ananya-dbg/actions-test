package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DemoApp {

    private static final Logger LOGGER = Logger.getLogger(DemoApp.class.getName());

    public boolean processValue(int value) {
        // Enforce camelCase variables (Checkstyle Rule)
        int calculatedResult = value * 2;

        if (calculatedResult > 10) {
            LOGGER.info("Value exceeded maximum allowed value.");
            return true;
        }
        return false;
    }

    public void safeDatabaseQuery(String userInput) {
        String query = "SELECT * FROM users WHERE username = ?"; // Safe parameterized query
        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:test", "sa", "");
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, userInput);
            stmt.execute();

        } catch (SQLException e) {
            // PMD requires catch blocks to have log statements or logic
            LOGGER.severe("Database error occurred: " + e.getMessage());
        }
    }
}
