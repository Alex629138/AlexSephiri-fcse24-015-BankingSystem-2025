package com.ooadassignment.bankingsystemtest.controller;

import com.ooadassignment.bankingsystemtest.HelloApplication;
import com.ooadassignment.bankingsystemtest.dao.*;
import com.ooadassignment.bankingsystemtest.model.*;
import com.ooadassignment.bankingsystemtest.util.ValidationUtil;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class UserController {
    @FXML
    private Button profileButton;

    private User user;

    @FXML
    private Label firstName, lastName, idNumber, dateOfBirth, dateOfRegistration;


    public UserController(Button profileButton, User user, Label firstName, Label lastName, Label idNumber, Label dateOfBirth, Label dateOfRegistration) {
        this.profileButton = profileButton;
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.dateOfBirth = dateOfBirth;
        this.dateOfRegistration = dateOfRegistration;
    }

    @FXML
    protected void showProfileScreen() throws IOException {

        Stage stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();

        showFirstName();
        showLastName();
        showIdNumber();
        showDateOfBirth();
        showDateOfRegistration();
    }

    @FXML
    protected void showFirstName(){
        firstName.setText(user.getFirst_name());
    }

    @FXML
    protected void showLastName(){
        lastName.setText(user.getLast_name());
    }

    @FXML
    protected void showIdNumber(){
        idNumber.setText(String.valueOf((user.getFirst_name())));
    }

    @FXML
    protected void showDateOfBirth(){
        dateOfBirth.setText(String.valueOf(user.getDate_of_birth()));
    }

    @FXML
    protected void showDateOfRegistration(){
        dateOfRegistration.setText(String.valueOf(user.getRegistration_date()));
    }

}
