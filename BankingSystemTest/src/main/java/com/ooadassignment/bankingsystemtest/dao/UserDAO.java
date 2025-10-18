package com.ooadassignment.bankingsystemtest.dao;

import com.ooadassignment.bankingsystemtest.model.User;
import com.ooadassignment.bankingsystemtest.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
