package com.ooadassignment.bankingsystemtest.dao;

import com.ooadassignment.bankingsystemtest.model.User;
import com.ooadassignment.bankingsystemtest.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public void createCustomer(User user) throws SQLException{
        String sql = "INSERT INTO customers VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, user.getCustomer_id());
            stmt.setString(2, user.getFirst_name());
            stmt.setString(3, user.getLast_name());
            stmt.setString(4, user.getEmail());
            stmt.setInt(5, user.getPhone_number());
            stmt.setString(6, user.getAddress());
            stmt.setDate(7, user.getDate_of_birth());
            stmt.setInt(8, user.getSsn());
            stmt.setDate(9, user.getRegistration_date());
            stmt.executeUpdate();

            System.out.println("Customer created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error Creating customer");
        }
    }

    public void exit(){
        System.exit(0);
    }

    public static List<User> viewAllCustomers() throws SQLException {
        String sql = "SELECT * FROM customers";
        List<User> customers = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setCustomer_id(rs.getInt("customer_id"));
                user.setFirst_name(rs.getString("first_name"));
                user.setLast_name(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPhone_number(rs.getInt("phone_number"));
                user.setAddress(rs.getString("address"));
                user.setSsn(rs.getInt("ssn"));
                user.setDate_of_birth(rs.getDate("date_of_birth"));
                user.setRegistration_date(rs.getDate("registration_date"));

                customers.add(user);

            }

            System.out.println("Successfully retrieved " + customers.size() + " customers from database!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error retrieving customers: " + e.getMessage());
            throw e;
        }

        return customers;
    }

    public static void main(String[] args){
        try {
            List<User> customers = viewAllCustomers();

            if (customers.isEmpty()) {
                System.out.println("No customers found in the database.");
            } else {
                System.out.println("\n=== ALL CUSTOMER DETAILS ===");
                for (User customer : customers) {
                    System.out.println(customer.toString());
                    System.out.println("-----------------------------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }
}
