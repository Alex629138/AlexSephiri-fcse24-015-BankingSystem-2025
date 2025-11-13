package com.ooadassignment.bankingsystemtest.controller;

import com.ooadassignment.bankingsystemtest.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileController {

    private User currentUser;

    @FXML
    private Label firstName, lastName, idNumber, dateOfBirth, dateOfRegistration;

    public ProfileController() {}

    public void setUser(User user){
        this.currentUser = user;
    }

    @FXML
    private void displayUserInfo() throws IOException{

        if (currentUser != null) {
            if (firstName != null) {
                firstName.setText(currentUser.getFirst_name());
            }
            if (lastName != null) {
                lastName.setText(currentUser.getLast_name());
            }
            if (idNumber != null) {
                idNumber.setText(String.valueOf(currentUser.getCustomer_id()));
            }
            if (dateOfBirth != null) {
                dateOfBirth.setText(String.valueOf(currentUser.getRegistration_date()));
            }
            if (dateOfRegistration != null) {
                dateOfRegistration.setText(String.valueOf(currentUser.getRegistration_date()));
            }
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/ooadassignment/bankingsystemtest/view/profile.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void showDepositScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/ooadassignment/bankingsystemtest/view/deposit.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}
