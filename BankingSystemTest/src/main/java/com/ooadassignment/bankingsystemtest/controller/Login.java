package com.ooadassignment.bankingsystemtest.controller;

import com.ooadassignment.bankingsystemtest.HelloApplication;
import com.ooadassignment.bankingsystemtest.model.User;
import com.ooadassignment.bankingsystemtest.util.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login {

    @FXML
    public Button loginButton;

    @FXML
    private TextField idNumberField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    private User user;

    @FXML
    protected void logIn() {
        String userId = idNumberField.getText();
        int idNumber = Integer.parseInt(userId);
        String password = passwordField.getText();

        if (userId.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Account number and password are required.");
            return;
        }

        String sql = "SELECT customer_id, first_name, last_name, email, phone_number, address, " +
                "date_of_birth, registration_date, encrypted_password " +
                "FROM customers WHERE customer_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idNumber);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedPassword = rs.getString("encrypted_password");

                    if (password.equals(storedPassword)) {
                        User foundUser = new User();
                        foundUser.setCustomer_id(rs.getInt("customer_id"));
                        foundUser.setFirst_name(rs.getString("first_name"));
                        foundUser.setLast_name(rs.getString("last_name"));
                        foundUser.setEmail(rs.getString("email"));
                        foundUser.setPhone_number(rs.getString("phone_number"));
                        foundUser.setAddress(rs.getString("address"));
                        foundUser.setDate_of_birth(rs.getDate("date_of_birth"));
                        foundUser.setRegistration_date(rs.getDate("registration_date"));

                        this.user = foundUser;
                        messageLabel.setText("Welcome back " + user.getFirst_name() + " " + user.getLast_name());

                        switchToHomeScreen();

                    } else {
                        messageLabel.setText("Invalid credentials.");
                    }
                } else {
                    messageLabel.setText("Invalid credentials.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Database error.");
        }
    }

    @FXML
    private void switchToHomeScreen() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Home Page");
        stage.setScene(scene);
        stage.show();
    }

}