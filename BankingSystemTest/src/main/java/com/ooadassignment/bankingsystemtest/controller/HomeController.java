package com.ooadassignment.bankingsystemtest.controller;

import com.ooadassignment.bankingsystemtest.dao.AccountDAO;
import com.ooadassignment.bankingsystemtest.model.Investment;
import com.ooadassignment.bankingsystemtest.model.Savings;
import com.ooadassignment.bankingsystemtest.model.User;
import com.ooadassignment.bankingsystemtest.session.UserSession;
import com.ooadassignment.bankingsystemtest.util.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class HomeController {

    @FXML
    private Button profileButton, depositButton, withdrawButton;

    @FXML
    private Label firstName, lastName, savingsBalanceLabel, investmentBalanceLabel;

    private User user;
    private AccountDAO accountDAO;
    private SavingsController savingsController;
    private InvestementController investementController;


    public HomeController(){
        this.accountDAO = new AccountDAO();
        this.savingsController = new SavingsController();
        this.investementController = new InvestementController();
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
    }

    private void loadAccountData() {
        try {
            Savings savings = accountDAO.getSavingsAccount(user.getCustomer_id());

            if (savings != null) {

                savingsBalanceLabel.setText(String.format("$%.2f", savings.getBalance()));

            }else{

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("No savings account available!");
                alert.setHeaderText("Do you want to create one now?");

                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    System.out.println("Savings Account creation clicked!");

                    try{

                        savingsController.createSavingsAccount(user.getCustomer_id());

                        Alert savingsAlert = new Alert(Alert.AlertType.CONFIRMATION);

                        savingsAlert.setTitle("Savings Account Creation");
                        savingsAlert.setHeaderText("Savings account successfully created");
                        savingsAlert.showAndWait();
                        loadAccountData();

                    } catch(SQLException e){
                        e.printStackTrace();
                    }

                } else {

                    System.out.println("Savings account created not clicked!.");

                }
            }
            Investment investment = accountDAO.getInvestmentAccount(user.getCustomer_id());

            if (investment != null) {
                investmentBalanceLabel.setText(String.format("$%.2f", investment.getBalance()));
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("No investment account available!");
                alert.setHeaderText("Do you want to create one now?");

                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    System.out.println("Investment Account creation clicked!");

                    try{

                        investementController.createInvestmentAccount(user.getCustomer_id());

                        Alert investmentAlert = new Alert(Alert.AlertType.CONFIRMATION);

                        investmentAlert.setTitle("Investment Account Creation");
                        investmentAlert.setHeaderText("Investment account successfully created");
                        investmentAlert.showAndWait();
                        loadAccountData();

                    } catch(SQLException e){
                        e.printStackTrace();
                    }

                } else {

                    System.out.println("Investment account created not clicked!.");

                }
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

    public void backToHome() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ooadassignment/bankingsystemtest/view/home.fxml"));
        Scene scene = new Scene(loader.load(), 600, 400);
        Stage stage = (Stage) depositButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
