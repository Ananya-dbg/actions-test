package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DemoApp {

    private static final Logger LOGGER = Logger.getLogger(DemoApp.class.getName());
    private static final int MAX_ALLOWED_VALUE = 10; // Extract magic numbers to constant (PMD Rule)

    public boolean processValue(int value) {
        // Enforce camelCase variables (Checkstyle Rule)
        int calculatedResult = value * 2;

        if (calculatedResult > MAX_ALLOWED_VALUE) {
            LOGGER.info("Value exceeded maximum allowed value.");
            return true;
        }
        return false;
    }

    public void safeDatabaseQuery(String userInput) {
        String query = "SELECT * FROM users WHERE username = ?"; // Safe parameterized query
        
        // FIX: Removed username and empty password parameters to resolve SpotBugs credential alerts
        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:test");
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, userInput);
            stmt.execute();

        } catch (SQLException e) {
            // FIX: Use Java logger's native Throwable signature to prevent CRLF Log Injection
            LOGGER.log(Level.SEVERE, "Database error occurred", e);
        }
    }
}
