package com.ooadassignment.bankingsystemtest.controller;

import com.ooadassignment.bankingsystemtest.model.*;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class UserController {

    public User user;

    public UserController(){}

    @FXML
    private void showDepositScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/ooadassignment/bankingsystemtest/view/deposit.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void setUser(User newUser){
        this.user = newUser;
    }

}
