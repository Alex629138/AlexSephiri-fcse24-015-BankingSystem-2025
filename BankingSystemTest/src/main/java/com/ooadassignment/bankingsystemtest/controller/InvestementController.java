package com.ooadassignment.bankingsystemtest.controller;

import com.ooadassignment.bankingsystemtest.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InvestementController {
    public InvestementController(){}

    public void createInvestmentAccount(int customerId) throws SQLException {
        String sql = "INSERT INTO investments (customer_id, balance, interest_rate) VALUES(?, ?, ?)";

        try(Connection conn = DBConnection.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, customerId);
            stmt.setFloat(2, 0.0F);
            stmt.setFloat(3, 15.0F);
            stmt.executeUpdate();
        }
    }
}
