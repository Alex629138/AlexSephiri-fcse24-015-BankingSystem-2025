package com.ooadassignment.bankingsystemtest.controller;

import com.ooadassignment.bankingsystemtest.model.User;
import com.ooadassignment.bankingsystemtest.util.DBConnection;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Login {

    private User user;

    @FXML
    public Button loginButton;

    @FXML
    private TextField idNumberField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    protected void logIn() {

        String userId = idNumberField.getText();
        int idNumber = Integer.parseInt(userId);
        String password = passwordField.getText();

        if (userId.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Account number and password are required.");
        }else{
            String sql = "SELECT * FROM customers WHERE customer_id = ?";

            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, idNumber);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String storedPassword = rs.getString("encrypted_password");

                        if (password.equals(storedPassword)) {
                            int customerId = rs.getInt("customer_id");
                            String firstName = rs.getString("first_name");
                            String lastName = rs.getString("last_name");
                            String email = rs.getString("email");
                            String phoneNumber = rs.getString("phone_number");
                            String address = rs.getString("address");
                            java.sql.Date dateOfBrith = rs.getDate("date_of_birth");
                            java.sql.Date registrationDate = rs.getDate("registration_date");

                            user = new User(customerId, firstName, lastName, email, phoneNumber, address, dateOfBrith, registrationDate);

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

    }

    @FXML
    private void switchToHomeScreen() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/ooadassignment/bankingsystemtest/view/home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        HomeController homeController = fxmlLoader.getController();
        homeController.setUser(user);

        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}