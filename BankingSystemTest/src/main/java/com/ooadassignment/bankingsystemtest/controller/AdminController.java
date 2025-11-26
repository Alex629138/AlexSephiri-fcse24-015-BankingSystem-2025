package com.ooadassignment.bankingsystemtest.controller;

import com.ooadassignment.bankingsystemtest.model.User;
import com.ooadassignment.bankingsystemtest.util.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML private TableView<User> customersTableView;
    @FXML private TableColumn<User, Integer> colId;
    @FXML private TableColumn<User, String> colFirstName;
    @FXML private TableColumn<User, String> colLastName;
    @FXML private TableColumn<User, String> colEmail;
    @FXML private TableColumn<User, String> colPhone;
    @FXML private TableColumn<User, String> colAddress;
    @FXML private TableColumn<User, Date> colDob;
    @FXML private TableColumn<User, Date> colRegistration;

    @FXML private Button clearFormButton;

    @FXML private TextField txtCustomerId;
    @FXML private TextField txtFirstName;
    @FXML private TextField txtLastName;
    @FXML private TextField txtEmail;
    @FXML private TextField txtPhone;
    @FXML private TextField txtAddress;
    @FXML private DatePicker dobPicker;
    @FXML private DatePicker registrationPicker;

    private final ObservableList<User> customers = FXCollections.observableArrayList();

    public AdminController() {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupTable();
        loadCustomers();
        customersTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> populateForm(newVal));
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
        customersTableView.setItems(customers);
    }

    private void loadCustomers() {
        customers.clear();
        String sql = "SELECT * FROM customers ORDER BY customer_id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                customers.add(new User(
                        rs.getInt("customer_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getString("address"),
                        rs.getDate("date_of_birth"),
                        rs.getDate("registration_date")
                ));
            }
        } catch (SQLException e) {
            showError("Failed to load customers", e);
        }
    }

    private void populateForm(User user) {
        if (user == null) {
            clearForm();
            return;
        }
        txtCustomerId.setText(String.valueOf(user.getCustomer_id()));
        txtFirstName.setText(user.getFirst_name());
        txtLastName.setText(user.getLast_name());
        txtEmail.setText(user.getEmail());
        txtPhone.setText(user.getPhone_number());
        txtAddress.setText(user.getAddress());
        dobPicker.setValue(user.getDate_of_birth() != null ? user.getDate_of_birth().toLocalDate() : null);
        registrationPicker.setValue(user.getRegistration_date() != null ? user.getRegistration_date().toLocalDate() : null);
    }

    @FXML
    private void clearForm() {
        txtCustomerId.clear();
        txtFirstName.clear();
        txtLastName.clear();
        txtEmail.clear();
        txtPhone.clear();
        txtAddress.clear();
        dobPicker.setValue(null);
        registrationPicker.setValue(null);
    }

    @FXML
    private void handleCreateCustomer() {
        if (!validateInput()) return;

        String sql = "INSERT INTO customers (first_name, last_name, email, phone_number, address, date_of_birth, registration_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            bindStatement(stmt);
            stmt.executeUpdate();
            showInfo("Customer created successfully");
            loadCustomers();
            clearForm();
        } catch (SQLException e) {
            showError("Failed to create customer", e);
        }
    }

    @FXML
    private void handleUpdateCustomer() {
        if (txtCustomerId.getText().isEmpty()) {
            showWarning("Select a customer to update.");
            return;
        }
        if (!validateInput()) return;

        String sql = "UPDATE customers SET first_name=?, last_name=?, email=?, phone_number=?, address=?, date_of_birth=?, registration_date=? WHERE customer_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            bindStatement(stmt);
            stmt.setInt(8, Integer.parseInt(txtCustomerId.getText()));
            stmt.executeUpdate();
            showInfo("Customer updated successfully");
            loadCustomers();
        } catch (SQLException e) {
            showError("Failed to update customer", e);
        }
    }

    @FXML
    private void handleDeleteCustomer() {
        if (txtCustomerId.getText().isEmpty()) {
            showWarning("Select a customer to delete.");
            return;
        }

        String sql = "DELETE FROM customers WHERE customer_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, Integer.parseInt(txtCustomerId.getText()));
            stmt.executeUpdate();
            showInfo("Customer deleted successfully");
            loadCustomers();
            clearForm();
        } catch (SQLException e) {
            showError("Failed to delete customer", e);
        }
    }

    private void bindStatement(PreparedStatement stmt) throws SQLException {
        stmt.setString(1, txtFirstName.getText().trim());
        stmt.setString(2, txtLastName.getText().trim());
        stmt.setString(3, txtEmail.getText().trim());
        stmt.setString(4, txtPhone.getText().trim());
        stmt.setString(5, txtAddress.getText().trim());
        stmt.setDate(6, toSqlDate(dobPicker.getValue()));
        stmt.setDate(7, toSqlDate(registrationPicker.getValue()));
    }

    private boolean validateInput() {
        if (txtFirstName.getText().isBlank() || txtLastName.getText().isBlank() ||
                txtEmail.getText().isBlank() || txtPhone.getText().isBlank()) {
            showWarning("First name, last name, email, and phone are required.");
            return false;
        }
        if (dobPicker.getValue() == null || registrationPicker.getValue() == null) {
            showWarning("Please pick both DOB and registration dates.");
            return false;
        }
        return true;
    }

    private Date toSqlDate(LocalDate date) {
        return date != null ? Date.valueOf(date) : null;
    }

    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    private void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    private void showError(String message, Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(message);
        alert.setContentText(e.getMessage());
        alert.show();
    }
}