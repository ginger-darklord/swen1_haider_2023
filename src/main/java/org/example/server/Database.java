package org.example.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private String connectionString = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgres";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(connectionString);
    }
}
