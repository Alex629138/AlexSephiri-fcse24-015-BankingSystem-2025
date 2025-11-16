package com.ooadassignment.bankingsystemtest.controller;

import com.ooadassignment.bankingsystemtest.model.User;
import com.ooadassignment.bankingsystemtest.util.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserController {

    @FXML
    private TableView<User> customersTableView;
    @FXML private TableColumn<User, Integer> colId;
    @FXML private TableColumn<User, String> colFirstName;
    @FXML private TableColumn<User, String> colLastName;
    @FXML private TableColumn<User, String> colEmail;
    @FXML private TableColumn<User, String> colPhone;
    @FXML private TableColumn<User, String> colAddress;
    @FXML private TableColumn<User, java.sql.Date> colDob;
    @FXML private TableColumn<User, java.sql.Date> colRegistration;

    private ObservableList<User> customersList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setupTable();
        loadCustomers();
    }

    private void setupTable() {
        colId.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("date_of_birth"));
        colRegistration.setCellValueFactory(new PropertyValueFactory<>("registration_date"));
        customersTableView.setItems(customersList);
    }

    private void loadCustomers() {
        customersList.clear();
        String sql = "SELECT * FROM customers";
        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                User user = new User(
                        rs.getInt("customer_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getString("address"),
                        rs.getDate("date_of_birth"),
                        rs.getDate("registration_date")
                );
                customersList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}