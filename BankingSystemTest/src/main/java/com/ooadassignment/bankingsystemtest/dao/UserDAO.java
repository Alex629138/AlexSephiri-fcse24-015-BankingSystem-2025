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
        String sql = "INSERT INTO customers (first_name, last_name, email, phone_number, address, date_of_birth, registration_date) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, user.getFirst_name());
            stmt.setString(2, user.getLast_name());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhone_number());
            stmt.setString(5, user.getAddress());
            stmt.setDate(6, user.getDate_of_birth());
            stmt.setDate(7, user.getRegistration_date());
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



    public static void main(String[] args){

    }
}
