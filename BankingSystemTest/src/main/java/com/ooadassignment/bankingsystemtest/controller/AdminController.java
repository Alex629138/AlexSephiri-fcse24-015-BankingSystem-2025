package com.ooadassignment.bankingsystemtest.controller;

import com.ooadassignment.bankingsystemtest.dao.UserDAO;
import com.ooadassignment.bankingsystemtest.model.User;
import com.ooadassignment.bankingsystemtest.util.DBConnection;
import com.ooadassignment.bankingsystemtest.view.AdminView;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminController {
    private final UserDAO dao;
    private final AdminView view;
    private final SimpleDateFormat dateFormat;

    public AdminController() {
        dao = new UserDAO();
        view = new AdminView();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public void run() throws ParseException, SQLException {
        Scanner inputScanner = new Scanner(System.in);

        while(true){
            view.showMenu();

            try {
                int choice = inputScanner.nextInt();
                inputScanner.nextLine();

                switch (choice){
                    case 1-> createCustomer(inputScanner);
                    case 2-> viewAllCustomers();
                    case 8-> {
                        System.out.println("Exiting...");
                        inputScanner.close();
                        dao.exit();
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                inputScanner.nextLine();
            }
        }
    }

    public void createCustomer(Scanner scanner) throws ParseException, SQLException {
        System.out.println("\n=== CREATE NEW CUSTOMER ===");

        System.out.print("Enter first name: ");
        String first_name = scanner.nextLine();

        System.out.print("Enter last name: ");
        String last_name = scanner.nextLine();

        System.out.print("Enter phone number: ");
        String phone_number_str = scanner.nextLine();

        System.out.print("Enter email address: ");
        String email = scanner.nextLine();

        System.out.print("Enter physical address: ");
        String address = scanner.nextLine();

        System.out.print("Enter date of birth (yyyy-MM-dd): ");
        String dob = scanner.nextLine();

        System.out.print("Enter SSN: ");
        String ssn_str = scanner.nextLine();

        System.out.print("Enter date of registration (yyyy-MM-dd): ");
        String registrationDate = scanner.nextLine();

        Date date_of_birth = null;
        Date registration_date = null;
        try {
            if (!dob.trim().isEmpty()) {
                java.util.Date DateOfBirth = dateFormat.parse(dob.trim());
                date_of_birth = new Date(DateOfBirth.getTime());
            }

            if (!registrationDate.trim().isEmpty()) {
                java.util.Date RegistrationDate = dateFormat.parse(registrationDate.trim());
                registration_date = new Date(RegistrationDate.getTime());
            }
        } catch (ParseException e) {
            System.out.println("Error parsing dates. Please use format yyyy-MM-dd");
            System.out.println("Date of birth: '" + dob + "'");
            System.out.println("Registration date: '" + registrationDate + "'");
            e.printStackTrace();
            return;
        }

        User newCustomer = new User(0, first_name, last_name, email, phone_number_str, address, ssn_str, date_of_birth, registration_date);

        try {
            dao.createCustomer(newCustomer);
            System.out.println("Customer created successfully!");
        } catch (SQLException e) {
            System.out.println("Error creating customer: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<User> viewAllCustomers() throws SQLException {
        String sql = "SELECT customer_id, first_name, last_name, email, phone_number, address, ssn, date_of_birth, registration_date FROM customers";
        List<User> customers = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setCustomer_id(rs.getInt("customer_id"));
                user.setFirst_name(rs.getString("first_name"));
                user.setLast_name(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPhone_number(rs.getString("phone_number"));
                user.setAddress(rs.getString("address"));
                user.setSsn(rs.getString("ssn"));
                user.setDate_of_birth(rs.getDate("date_of_birth"));
                user.setRegistration_date(rs.getDate("registration_date"));

                customers.add(user);
            }

            System.out.println("Successfully retrieved " + customers.size() + " customers from database!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error retrieving customers: " + e.getMessage());
            throw e;
        }

        if (customers.isEmpty()) {
            System.out.println("No customers found in the database.");
        } else {
            System.out.println("\n=== ALL CUSTOMER DETAILS ===");
            for (User customer : customers) {
                System.out.println(customer.toString());
                System.out.println("-----------------------------");
            }
        }

        return customers;
    }

}
