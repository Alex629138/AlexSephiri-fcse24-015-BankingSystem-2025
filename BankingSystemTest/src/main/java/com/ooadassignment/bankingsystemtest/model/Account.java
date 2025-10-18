package com.ooadassignment.bankingsystemtest.model;

import java.sql.Date;

public class Account extends User{
    private int accountId;
    private double balance;
    private String accountType, branch;

    public Account(){}

    public Account(int customer_id, String first_name, String last_name, String email, int phone_number, String address, int ssn, Date date_of_birth, Date registration_date, int accountId, double balance, String accountType, String branch) {
        super(customer_id, first_name, last_name, email, phone_number, address, ssn, date_of_birth, registration_date);
        this.accountId = accountId;
        this.balance = balance;
        this.accountType = accountType;
        this.branch = branch;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                ", accountType='" + accountType + '\'' +
                ", branch='" + branch + '\'' +
                '}';
    }
}
