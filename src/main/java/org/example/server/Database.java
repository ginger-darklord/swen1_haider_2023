package org.example.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private String connectionString = "jdbc:postgresql://localhost:5432/database?user=admin&password=password";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(connectionString);
    }
}
