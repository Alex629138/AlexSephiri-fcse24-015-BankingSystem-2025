package com.ooadassignment.bankingsystemtest.controller;

import com.ooadassignment.bankingsystemtest.util.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepositController {

    @FXML
    private TextField recipientIdNumber, amountToDeposit;

    @FXML
    private Label successMessage, errorMessage;

    @FXML
    public void depositToAccount(){
        String accountNumber = recipientIdNumber.getText();
        String depositAmount = amountToDeposit.getText();

        // Validation
        if (accountNumber.isEmpty() || depositAmount.isEmpty()) {
            errorMessage.setText("Please fill in all fields.");
            return;
        }

        try {
            int customerId = Integer.parseInt(accountNumber);
            BigDecimal amount = new BigDecimal(depositAmount);

            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                errorMessage.setText("Amount must be greater than zero.");
                return;
            }

            // First, get the current balance
            String selectSql = "SELECT available_balance FROM customers WHERE customer_id = ?";
            String updateSql = "UPDATE customers SET available_balance = available_balance + ? WHERE customer_id = ?";

            try (Connection conn = DBConnection.getConnection()) {

                // Check if customer exists
                try (PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
                    selectStmt.setInt(1, customerId);
                    ResultSet rs = selectStmt.executeQuery();

                    if (!rs.next()) {
                        errorMessage.setText("Customer not found.");
                        return;
                    }
                }

                // Perform the deposit (add to current balance)
                try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                    updateStmt.setBigDecimal(1, amount);  // The amount to add
                    updateStmt.setInt(2, customerId);     // The customer ID

                    int rowsAffected = updateStmt.executeUpdate();

                    if (rowsAffected > 0) {
                        successMessage.setText("Deposit successful! Amount: P" + amount);
                        // Clear fields
                        recipientIdNumber.clear();
                        amountToDeposit.clear();
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

}