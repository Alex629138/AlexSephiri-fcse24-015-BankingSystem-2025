package com.ooadassignment.bankingsystemtest.controller;

import com.ooadassignment.bankingsystemtest.model.User;
import com.ooadassignment.bankingsystemtest.session.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileController {

    @FXML
    private Label firstName, lastName, idNumber, dateOfBirth, dateOfRegistration;

    @FXML
    private Button profileButton;

    private User user;

    public void initialize() {
        user = UserSession.getInstance().getLoggedInUser();
        if (user != null) {
            firstName.setText(user.getFirst_name());
            lastName.setText(user.getLast_name());
            idNumber.setText(String.valueOf(user.getCustomer_id()));
            dateOfBirth.setText(user.getDate_of_birth().toString());
            dateOfRegistration.setText(user.getRegistration_date().toString());
        }
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
