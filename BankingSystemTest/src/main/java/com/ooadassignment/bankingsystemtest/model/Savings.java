package com.ooadassignment.bankingsystemtest.model;

import java.sql.Date;

public class Savings extends Account{
    private int savingsAccountId;
    private double balance, interestRate;

    public Savings() {}

    public Savings(int customer_id, String first_name, String last_name, String email, int phone_number, String address, int ssn, Date date_of_birth, Date registration_date, int accountId, double balance, String accountType, String branch, int savingsAccountId, double balance1, double interestRate) {
        super(customer_id, first_name, last_name, email, phone_number, address, ssn, date_of_birth, registration_date, accountId, balance, accountType, branch);
        this.savingsAccountId = savingsAccountId;
        this.balance = balance1;
        this.interestRate = interestRate;
    }

    public int getSavingsAccountId() {
        return savingsAccountId;
    }

    public void setSavingsAccountId(int savingsAccountId) {
        this.savingsAccountId = savingsAccountId;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public String toString() {
        return "Savings{" +
                "savingsAccountId=" + savingsAccountId +
                ", balance=" + balance +
                ", interestRate=" + interestRate +
                '}';
    }
}
