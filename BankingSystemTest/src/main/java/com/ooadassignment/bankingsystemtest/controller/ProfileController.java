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

    @FXML
    public Button profileButton;

    public ProfileController() {}

    private void loadUserInfo() {
        if (currentUser == null) return;

        firstName.setText(Session.currentUser.getFirst_name());
        lastName.setText(Session.currentUser.getLast_name());
        idNumber.setText(String.valueOf(Session.currentUser.getCustomer_id()));
        dateOfBirth.setText(Session.currentUser.getDate_of_birth().toString());
        dateOfRegistration.setText(Session.currentUser.getRegistration_date().toString());
    }

    public void setUser(User user){
        this.currentUser = user;
        loadUserInfo();
    }

    @FXML
    private void goToProfile() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ooadassignment/bankingsystemtest/view/profile.fxml"));
        Scene scene = new Scene(loader.load(), 600, 400);

        ProfileController controller = loader.getController();
        controller.setUser(Session.currentUser);

        Stage stage = (Stage) profileButton.getScene().getWindow();
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
