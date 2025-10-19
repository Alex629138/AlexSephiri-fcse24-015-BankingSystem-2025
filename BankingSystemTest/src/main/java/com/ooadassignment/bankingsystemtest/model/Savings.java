package com.ooadassignment.bankingsystemtest.model;

import java.sql.Date;

public class Savings extends Account{
    private int savingsAccountId;
    private double balance, interestRate;

    public Savings() {}

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
                ", accountId=" + getAccountId() +
                ", customerId=" + getCustomer_id() +
                ", balance=" + getBalance() +
                ", accountType='" + getAccountType() + '\'' +
                '}';
    }
}
