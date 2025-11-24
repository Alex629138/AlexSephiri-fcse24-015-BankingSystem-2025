package com.ooadassignment.bankingsystemtest.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class EntryController {

    @FXML
    private Button customerButton;

    @FXML
    private Button organizationButton;

    @FXML
    private void goToCustomerLogin() throws IOException {
        switchScene(customerButton, "/com/ooadassignment/bankingsystemtest/login.fxml", 600, 400);
    }

    @FXML
    private void goToOrganizationLogin() throws IOException {
        switchScene(organizationButton, "/com/ooadassignment/bankingsystemtest/view/organization-login.fxml", 500, 400);
    }

    private void switchScene(Button sourceButton, String resourcePath, double width, double height) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(resourcePath));
        Scene scene = new Scene(loader.load(), width, height);
        Stage stage = (Stage) sourceButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
