package com.ooadassignment.bankingsystemtest.controller;

import com.ooadassignment.bankingsystemtest.dao.AccountDAO;
import com.ooadassignment.bankingsystemtest.model.Investment;
import com.ooadassignment.bankingsystemtest.model.Savings;
import com.ooadassignment.bankingsystemtest.model.User;
import com.ooadassignment.bankingsystemtest.session.UserSession;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class WithdrawController {

    @FXML
    private ComboBox<String> accountTypeComboBox;
    @FXML
    private TextField amountTextField;
    @FXML
    private Label messageLabel;

    private User loggedInUser;
    private AccountDAO accountDAO;

    @FXML
    public void initialize() {
        loggedInUser = UserSession.getInstance().getLoggedInUser();
        accountDAO = new AccountDAO();
        accountTypeComboBox.setItems(FXCollections.observableArrayList("Savings", "Investment"));
    }

    @FXML
    private void handleWithdraw() {
        String accountType = accountTypeComboBox.getValue();
        String amountStr = amountTextField.getText();

        if (accountType == null || amountStr.isEmpty()) {
            messageLabel.setText("Please select an account and enter an amount.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountStr);
            if (amount <= 0) {
                messageLabel.setText("Withdrawal amount must be positive.");
                return;
            }

            if (accountType.equals("Savings")) {
                Savings savings = accountDAO.getSavingsAccount(loggedInUser.getCustomer_id());
                if (savings != null) {
                    if (savings.getBalance() >= amount) {
                        double newBalance = savings.getBalance() - amount;
                        accountDAO.updateAccountBalance("Savings", savings.getSavings_account_id(), newBalance);
                        messageLabel.setText("Withdrawal successful!");
                    } else {
                        messageLabel.setText("Insufficient funds.");
                    }
                } else {
                    messageLabel.setText("Savings account not found.");
                }
            } else if (accountType.equals("Investment")) {
                Investment investment = accountDAO.getInvestmentAccount(loggedInUser.getCustomer_id());
                if (investment != null) {
                    if (investment.getBalance() >= amount) {
                        double newBalance = investment.getBalance() - amount;
                        accountDAO.updateAccountBalance("Investment", investment.getInvestment_account_id(), newBalance);
                        messageLabel.setText("Withdrawal successful!");
                    } else {
                        messageLabel.setText("Insufficient funds.");
                    }
                } else {
                    messageLabel.setText("Investment account not found.");
                }
            }
        } catch (NumberFormatException e) {
            messageLabel.setText("Invalid amount format.");
        } catch (SQLException e) {
            messageLabel.setText("Database error.");
            e.printStackTrace();
        }
    }
}
