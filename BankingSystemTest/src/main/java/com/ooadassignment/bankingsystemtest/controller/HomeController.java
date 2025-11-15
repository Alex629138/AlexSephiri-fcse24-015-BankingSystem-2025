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

public class HomeController {

    @FXML
    private Button profileButton;

    @FXML
    private Label firstName, lastName, dateOfBirth, dateOfRegistration;

    private User user;

    public HomeController(){}


    @FXML
    public void initialize() {
        this.user = UserSession.getInstance().getLoggedInUser();
        if (user != null) {
            loadUserData();
        }
    }

    private void loadUserData() {
        firstName.setText(user.getFirst_name());
        lastName.setText(user.getLast_name());
        dateOfBirth.setText(user.getDate_of_birth().toString());
        dateOfRegistration.setText(user.getRegistration_date().toString());
    }

    @FXML
    private void goToProfile() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ooadassignment/bankingsystemtest/view/profile.fxml"));
        Scene scene = new Scene(loader.load(), 600, 400);

        // The ProfileController will get the user from the session
        ProfileController profileController = loader.getController();

        Stage stage = (Stage) profileButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
