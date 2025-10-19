package com.ooadassignment.bankingsystemtest.dao;

import com.ooadassignment.bankingsystemtest.model.Savings;

import java.sql.SQLException;

public class SavingsDAO extends AccountDAO {

    public void createSavingsAccount(Savings savings) throws SQLException {
        createAccount(savings);
        System.out.println("Savings account created successfully!");
    }
}
