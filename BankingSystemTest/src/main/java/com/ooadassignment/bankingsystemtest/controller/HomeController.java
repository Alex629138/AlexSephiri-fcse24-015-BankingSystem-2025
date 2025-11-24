package com.ooadassignment.bankingsystemtest.controller;

import com.ooadassignment.bankingsystemtest.dao.AccountDAO;
import com.ooadassignment.bankingsystemtest.model.Investment;
import com.ooadassignment.bankingsystemtest.model.Savings;
import com.ooadassignment.bankingsystemtest.model.User;
import com.ooadassignment.bankingsystemtest.session.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class HomeController {

    @FXML
    private Button profileButton, depositButton, withdrawButton;

    @FXML
    private Label firstName, lastName, dateOfBirth, dateOfRegistration, savingsBalanceLabel, investmentBalanceLabel;

    private User user;
    private AccountDAO accountDAO;


    public HomeController(){
        this.accountDAO = new AccountDAO();
    }

    @FXML
    public void initialize() {
        this.user = UserSession.getInstance().getLoggedInUser();
        if (user != null) {
            loadUserData();
            loadAccountData();
        }
    }

    private void loadUserData() {
        firstName.setText(user.getFirst_name());
        lastName.setText(user.getLast_name());
        dateOfBirth.setText(user.getDate_of_birth().toString());
        dateOfRegistration.setText(user.getRegistration_date().toString());
    }

    private void loadAccountData() {
        try {
            Savings savings = accountDAO.getSavingsAccount(user.getCustomer_id());
            if (savings != null) {
                savingsBalanceLabel.setText(String.format("$%.2f", savings.getBalance()));
            }
            Investment investment = accountDAO.getInvestmentAccount(user.getCustomer_id());
            if (investment != null) {
                investmentBalanceLabel.setText(String.format("$%.2f", investment.getBalance()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToDeposit() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ooadassignment/bankingsystemtest/view/deposit.fxml"));
        Scene scene = new Scene(loader.load(), 600, 400);
        Stage stage = (Stage) depositButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToWithdraw() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ooadassignment/bankingsystemtest/view/withdraw.fxml"));
        Scene scene = new Scene(loader.load(), 600, 400);
        Stage stage = (Stage) withdrawButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToProfile() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ooadassignment/bankingsystemtest/view/profile.fxml"));
        Scene scene = new Scene(loader.load(), 600, 400);

        ProfileController profileController = loader.getController();

        Stage stage = (Stage) profileButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
