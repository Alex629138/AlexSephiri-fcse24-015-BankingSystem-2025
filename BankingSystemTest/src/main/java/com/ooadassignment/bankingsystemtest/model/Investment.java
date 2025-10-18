package com.ooadassignment.bankingsystemtest.model;

import java.sql.Date;

public class Investment extends Account{

    private int investmentAccountId;
    private double balance, interestRate;

    public Investment(){}

    public Investment(int customer_id, String first_name, String last_name, String email, int phone_number, String address, int ssn, Date date_of_birth, Date registration_date, int accountId, double balance, String accountType, String branch, int investmentAccountId, double balance1, double interestRate) {
        super(customer_id, first_name, last_name, email, phone_number, address, ssn, date_of_birth, registration_date, accountId, balance, accountType, branch);
        this.investmentAccountId = investmentAccountId;
        this.balance = balance1;
        this.interestRate = interestRate;
    }

    @Override
    public String toString() {
        return "Investment{" +
                "investmentAccountId=" + investmentAccountId +
                ", balance=" + balance +
                ", interestRate=" + interestRate +
                '}';
    }
}
