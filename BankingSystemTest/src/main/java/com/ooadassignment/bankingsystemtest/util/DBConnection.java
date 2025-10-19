package com.ooadassignment.bankingsystemtest.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL2 = "jdbc:postgresql://aws-1-us-east-1.pooler.supabase.com:6543/postgres?user=postgres.fvmemjaaxahgtarnhaxb&password=alexsephiri";

    public static Connection getConnection() throws SQLException {
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL JDBC Driver not found", e);
        }

        Connection conn = DriverManager.getConnection(URL2);

        if (conn != null){
            System.out.println("Database Connected!");
            return conn;
        }else{
            throw new SQLException("Database NOT connected!");
        }
    }

    public static void main(String[] args) {
        try {
            Connection conn = getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
