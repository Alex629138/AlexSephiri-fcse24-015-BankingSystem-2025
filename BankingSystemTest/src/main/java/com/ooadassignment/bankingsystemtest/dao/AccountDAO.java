package com.ooadassignment.bankingsystemtest.dao;

import com.ooadassignment.bankingsystemtest.model.Account;
import com.ooadassignment.bankingsystemtest.model.User;
import com.ooadassignment.bankingsystemtest.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AccountDAO {

    public void createAccount(Account account) throws SQLException {
        String sql = "INSERT INTO account (accountId, customer_id, accountType, accountHolder, balance, interestRate, isActive, registrationDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, account.getAccountId());
            stmt.setInt(2, account.getCustomer_id());
            stmt.setString(3, account.getAccountType());
            stmt.setString(4, account.getAccountHolder() != null ? account.getAccountHolder() : getAccountHolderName(account));
            stmt.setDouble(5, account.getBalance());
            stmt.setDouble(6, account.getInterestRate());
            stmt.setBoolean(7, account.isActive());
            stmt.setDate(8, account.getRegistrationDate());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Account created successfully!");
            } else {
                throw new SQLException("Failed to create account");
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error creating account: " + e.getMessage());
        }
    }

    private String getAccountHolderName(Account account) {
        return account.getFirst_name() + " " + account.getLast_name();
    }

    public Account getAccountById(int accountId) throws SQLException {
        return null;  
    }

    public void updateAccountBalance(int accountId, double newBalance) throws SQLException {
        
    }


}
