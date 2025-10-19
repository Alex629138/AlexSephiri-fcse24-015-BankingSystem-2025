package com.ooadassignment.bankingsystemtest;

import com.ooadassignment.bankingsystemtest.controller.AdminController;
import com.ooadassignment.bankingsystemtest.controller.UserController;

import java.sql.SQLException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws SQLException, ParseException {
        new UserController().handleMenu(869918513);
    }
}
