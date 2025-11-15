package com.ooadassignment.bankingsystemtest.controller;

import com.ooadassignment.bankingsystemtest.model.User;
import com.ooadassignment.bankingsystemtest.util.DBConnection;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepositController {

    User user = Session.currentUser;

    @FXML
    private TextField recipientIdNumber, amountToDeposit;

    @FXML
    private Label successMessage, errorMessage, availableBalance;

    @FXML
    public void depositToAccount() {
        String accountNumber = recipientIdNumber.getText();
        String depositAmount = amountToDeposit.getText();

        //Test
        System.out.println(Session.currentUser.getCustomer_id());
        System.out.println(Session.currentUser.getFirst_name());
        System.out.println(Session.currentUser.getLast_name());

        if (accountNumber.isEmpty() || depositAmount.isEmpty()) {
            errorMessage.setText("Please fill in all fields.");
            return;
        }

        try {
            int customerId = Integer.parseInt(accountNumber);
            double amount = Double.parseDouble(depositAmount);

            String selectSql = "SELECT balance FROM savings WHERE customer_id = ?";
            String updateSql = "UPDATE savings SET balance = balance + ? WHERE customer_id = ?";


            try (Connection conn = DBConnection.getConnection()) {

                try (PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
                    selectStmt.setInt(1, customerId);
                    ResultSet rs = selectStmt.executeQuery();

                    if (!rs.next()) {
                        errorMessage.setText("Customer not found.");
                        return;
                    }
                }

                try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                    updateStmt.setDouble(1, amount);
                    updateStmt.setInt(2, customerId);

                    int rowsAffected = updateStmt.executeUpdate();

                    if (rowsAffected > 0) {
                        successMessage.setText("Deposit successful!");
                        recipientIdNumber.clear();
                        amountToDeposit.clear();
                        availableBalance.setText(String.valueOf(getUserBalance(7) + amount));
                    } else {
                        errorMessage.setText("Deposit failed.");
                    }
                }
            }

        } catch (NumberFormatException e) {
            errorMessage.setText("Invalid account number or amount format.");
            e.printStackTrace();
        } catch (SQLException e) {
            errorMessage.setText("Database error occurred.");
            e.printStackTrace();
        }
    }

    public double getUserBalance(int customerId) throws SQLException {
        String sql = "SELECT balance FROM savings WHERE customer_id = ?";
        double balance = 0.0;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                balance = rs.getDouble("balance");
            }
        }

        return balance;
    }


}