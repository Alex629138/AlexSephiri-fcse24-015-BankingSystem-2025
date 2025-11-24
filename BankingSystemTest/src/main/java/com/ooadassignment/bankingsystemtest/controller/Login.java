package com.ooadassignment.bankingsystemtest.controller;

import com.ooadassignment.bankingsystemtest.model.User;
import com.ooadassignment.bankingsystemtest.session.UserSession;
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

    public Login(){}

    @FXML
    protected void logIn() {

        String userId = idNumberField.getText();
        if (userId.isEmpty()) {
            messageLabel.setText("Account number and password are required.");
            return;
        }

        int idNumber;
        try {
            idNumber = Integer.parseInt(userId);
        } catch (NumberFormatException e) {
            messageLabel.setText("Account number must be numeric.");
            return;
        }
        String password = passwordField.getText();

        if (password.isEmpty()) {
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
                            UserSession.getInstance().setLoggedInUser(user);

                            if (customerId == 1) {
                                switchToAdminScreen();
                            } else {
                                switchToHomeScreen();
                            }

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ooadassignment/bankingsystemtest/view/home.fxml"));
        Scene scene = new Scene(loader.load(), 600, 400);


        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void switchToAdminScreen() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ooadassignment/bankingsystemtest/view/admin.fxml"));
        Scene scene = new Scene(loader.load(), 600, 620);

        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


}