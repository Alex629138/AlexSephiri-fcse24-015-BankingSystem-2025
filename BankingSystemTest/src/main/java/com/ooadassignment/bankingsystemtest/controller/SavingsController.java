package com.ooadassignment.bankingsystemtest.controller;

import com.ooadassignment.bankingsystemtest.model.User;
import com.ooadassignment.bankingsystemtest.session.UserSession;
import com.ooadassignment.bankingsystemtest.util.DBConnection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SavingsController {

    public User user;

    public SavingsController(){}

    public void initialize(){
        this.user = UserSession.getInstance().getLoggedInUser();
    }

    public void createSavingsAccount(int customerId) throws SQLException {

        String sql = "INSERT INTO savings (customer_id, balance, interest_rate) VALUES(?, ?, ?)";

        try(Connection conn = DBConnection.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, customerId);
            stmt.setFloat(2, 0.0F);
            stmt.setFloat(3, 7.0F);
            stmt.executeUpdate();
        }
    }



}
