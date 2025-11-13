package com.ooadassignment.bankingsystemtest.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class UserView extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Load the hello-view.fxml file with UserController
        FXMLLoader fxmlLoader = new FXMLLoader(UserView.class.getResource("/com/ooadassignment/bankingsystemtest/hello-view.fxml"));
        Parent root = fxmlLoader.load();

        // Create scene and set it to the stage
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Trust Bank - User Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to launch the user view
    public static void showUserView() {
        launch();
    }

    // Method to show user view with a specific customer ID (for future enhancement)
    public static void showUserView(int customerId) {
        // Store customer ID for the controller to use
        System.setProperty("current.customer.id", String.valueOf(customerId));
        launch();
    }
}
