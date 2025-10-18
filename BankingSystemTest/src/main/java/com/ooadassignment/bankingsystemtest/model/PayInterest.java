package com.ooadassignment.bankingsystemtest.model;

import java.sql.Date;

public class PayInterest extends Account{

    private int payInterestId;
    private int userId, accountId;
    private double interestAmount;

    public PayInterest() {}

    public PayInterest(int customer_id, String first_name, String last_name, String email, int phone_number, String address, int ssn, Date date_of_birth, Date registration_date, int accountId, double balance, String accountType, String branch, int payInterestId, int userId, int accountId1, double interestAmount) {
        super(customer_id, first_name, last_name, email, phone_number, address, ssn, date_of_birth, registration_date, accountId, balance, accountType, branch);
        this.payInterestId = payInterestId;
        this.userId = userId;
        this.accountId = accountId1;
        this.interestAmount = interestAmount;
    }

    public int getPayInterestId() {
        return payInterestId;
    }

    public void setPayInterestId(int payInterestId) {
        this.payInterestId = payInterestId;
    }

    @Override
    public int getAccountId() {
        return accountId;
    }

    @Override
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(double interestAmount) {
        this.interestAmount = interestAmount;
    }

    @Override
    public String toString() {
        return "PayInterest{" +
                "payInterestId=" + payInterestId +
                ", userId=" + userId +
                ", accountId=" + accountId +
                ", interestAmount=" + interestAmount +
                '}';
    }
}
