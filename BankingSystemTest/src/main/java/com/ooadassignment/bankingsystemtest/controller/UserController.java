package com.ooadassignment.bankingsystemtest.controller;

import com.ooadassignment.bankingsystemtest.dao.UserDAO;
import com.ooadassignment.bankingsystemtest.model.User;
import com.ooadassignment.bankingsystemtest.view.UserView;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.sql.Date;

public class UserController {
    private final UserDAO dao;
    private final UserView view;
    private final Scanner sc;
    private final User user;
    private SimpleDateFormat dateFormat;

    public UserController() {
        dao = new UserDAO();
        view = new UserView();
        user = new User();
        sc = new Scanner(System.in);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public void run() throws ParseException, SQLException {
        while(true){
            view.showMenu();
            int choice = sc.nextInt();

            switch (choice){
                //case 1-> viewAllAccounts();
                case 2-> createCustomer();
                //case 3-> dao.viewInvestmentAccount();
                //case 4-> dao.viewChequeAccount();
                //case 5-> dao.viewCustomerProfile();
                //case 6-> dao.depositFunds();
                //case 7-> dao.withdrawFunds();
                case 8-> dao.exit();
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void createCustomer() throws ParseException, SQLException {
        System.out.println("Enter customer Id: ");
        int customer_id = sc.nextInt();

        System.out.println("Enter first name: ");
        String first_name = sc.next();

        System.out.println("Enter last name: ");
        String last_name = sc.next();

        System.out.println("Enter phone number: ");
        int phone_number = sc.nextInt();

        System.out.println("Enter email address: ");
        String email = sc.next();

        System.out.println("Enter physical address: ");
        String address = sc.next();

        System.out.println("Enter date of birth: ");
        String dob = sc.next();


        System.out.println("Enter SSN: ");
        int ssn = sc.nextInt();

        System.out.println("Enter date of registration: ");
        String registrationDate = sc.next();

        Date date_of_birth = null;
        Date registration_date = null;
        try {
            java.util.Date DateOfBirth = dateFormat.parse(dob);
            java.util.Date RegistrationDate = dateFormat.parse(registrationDate);
            date_of_birth = new Date(DateOfBirth.getTime());
            registration_date = new Date(RegistrationDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        dao.createCustomer(new User(customer_id, first_name, last_name, email, phone_number, address, ssn, date_of_birth, registration_date));
    }

}
