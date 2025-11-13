package com.ooadassignment.bankingsystemtest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage){
        try{
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                stage.setTitle("Login");
                stage.setScene(scene);
                stage.show();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
