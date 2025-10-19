package com.ooadassignment.bankingsystemtest.dao;

import com.ooadassignment.bankingsystemtest.model.Investment;

import java.sql.SQLException;

public class InvestmentDAO extends AccountDAO {
    private static final double MINIMUM_DEPOSIT = 500.0;

    public void createInvestmentAccount(Investment investment) throws SQLException {
        if (investment.getBalance() < MINIMUM_DEPOSIT) {
            throw new SQLException("Investment account requires a minimum deposit of $" + MINIMUM_DEPOSIT);
        }

        createAccount(investment);
        System.out.println("Investment account created successfully!");
    }

    public static double getMinimumDeposit() {
        return MINIMUM_DEPOSIT;
    }
}
