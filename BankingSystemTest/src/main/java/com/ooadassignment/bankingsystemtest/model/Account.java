package com.ooadassignment.bankingsystemtest.model;

import java.sql.Date;

public class Account extends User{
    private int accountId;
    private double balance, interestRate;
    private boolean isActive;
    private String accountType, branch, accountHolder;
    private Date registrationDate;

    public Account(){}

    public Account(int customer_id, String first_name, String last_name, String email, String phone_number, String address, String ssn, Date date_of_birth, Date registration_date, int accountId, double balance, String accountType, String branch) {
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

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                ", accountType='" + accountType + '\'' +
                ", branch='" + branch + '\'' +
                ", accountHolder='" + accountHolder + '\'' +
                ", interestRate=" + interestRate +
                ", isActive=" + isActive +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
