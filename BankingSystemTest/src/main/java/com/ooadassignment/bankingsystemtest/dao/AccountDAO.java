package com.ooadassignment.bankingsystemtest.dao;

import com.ooadassignment.bankingsystemtest.model.Account;
import com.ooadassignment.bankingsystemtest.model.Investment;
import com.ooadassignment.bankingsystemtest.model.Savings;
import com.ooadassignment.bankingsystemtest.model.User;
import com.ooadassignment.bankingsystemtest.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountDAO {

    protected static final double MINIMUM_INVESTMENT_DEPOSIT = 500.0;

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

    public Savings getSavingsAccount(int customerId) throws SQLException {
        String sql = "SELECT * FROM savings WHERE customer_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Savings(customerId, null, null, null, null, null, null, null, rs.getInt("savings_account_id"), rs.getDouble("balance"), "Savings", null, rs.getInt("savings_account_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error fetching savings account: " + e.getMessage());
        }
        return null;
    }

    public Investment getInvestmentAccount(int customerId) throws SQLException {
        String sql = "SELECT * FROM investments WHERE customer_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Investment(customerId, null, null, null, null, null, null, null, rs.getInt("investment_account_id"), rs.getDouble("balance"), "Investment", null, rs.getInt("investment_account_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error fetching investment account: " + e.getMessage());
        }
        return null;
    }

    public void updateAccountBalance(String accountType, int accountId, double newBalance) throws SQLException {
        String table = accountType.equalsIgnoreCase("Savings") ? "savings" : "investments";
        String accountIdColumn = accountType.equalsIgnoreCase("Savings") ? "savings_account_id" : "investment_account_id";
        String sql = "UPDATE " + table + " SET balance = ? WHERE " + accountIdColumn + " = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, newBalance);
            stmt.setInt(2, accountId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error updating account balance: " + e.getMessage());
        }
    }

    public boolean hasSavingsAccount(int customerId) throws SQLException {
        return getSavingsAccount(customerId) != null;
    }

    public boolean hasInvestmentAccount(int customerId) throws SQLException {
        return getInvestmentAccount(customerId) != null;
    }

    public Savings openSavingsAccount(int customerId, double initialDeposit) throws SQLException {
        if (initialDeposit < 0) {
            throw new SQLException("Initial deposit cannot be negative.");
        }
        if (hasSavingsAccount(customerId)) {
            throw new SQLException("Customer already has a savings account.");
        }

        String sql = "INSERT INTO savings (customer_id, balance) VALUES (?, ?) RETURNING savings_account_id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            stmt.setDouble(2, initialDeposit);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    throw new SQLException("Failed to create savings account");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error creating savings account: " + e.getMessage());
        }

        return getSavingsAccount(customerId);
    }

    public Investment openInvestmentAccount(int customerId, double initialDeposit) throws SQLException {
        if (initialDeposit < MINIMUM_INVESTMENT_DEPOSIT) {
            throw new SQLException("Investment accounts require a minimum deposit of $" + MINIMUM_INVESTMENT_DEPOSIT);
        }
        if (hasInvestmentAccount(customerId)) {
            throw new SQLException("Customer already has an investment account.");
        }

        String sql = "INSERT INTO investments (customer_id, balance) VALUES (?, ?) RETURNING investment_account_id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            stmt.setDouble(2, initialDeposit);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    throw new SQLException("Failed to create investment account");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error creating investment account: " + e.getMessage());
        }

        return getInvestmentAccount(customerId);
    }


}
