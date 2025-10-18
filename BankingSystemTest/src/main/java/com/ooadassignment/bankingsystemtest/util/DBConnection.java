package com.ooadassignment.bankingsystemtest.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/users";
    private static final String USER = "postgres";
    private static final String PASSWORD = "alex123";

    public static Connection getConnection() throws SQLException {
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL JDBC Driver not found", e);
        }

        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

        if (conn != null){
            System.out.println("Database Connected!");
            return conn;
        }else{
            throw new SQLException("Database NOT connected!");
        }
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        try {
            Connection conn = getConnection();
            closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
