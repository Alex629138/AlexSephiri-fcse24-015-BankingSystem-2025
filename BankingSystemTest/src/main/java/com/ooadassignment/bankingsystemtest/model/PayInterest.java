package com.ooadassignment.bankingsystemtest.model;

import java.sql.Date;

public class PayInterest extends Account {

    private int payInterestId;
    private double interestAmount;

    public PayInterest() {}

    public PayInterest(int customer_id, String first_name, String last_name, String email, String phone_number, String address, Date date_of_birth, Date registration_date, int accountId, double balance, String accountType, String branch, int payInterestId, double interestAmount) {
        super(customer_id, first_name, last_name, email, phone_number, address, date_of_birth, registration_date, accountId, balance, accountType, branch);
        this.payInterestId = payInterestId;
        this.interestAmount = interestAmount;
    }

    public int getPayInterestId() {
        return payInterestId;
    }

    public void setPayInterestId(int payInterestId) {
        this.payInterestId = payInterestId;
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
                ", interestAmount=" + interestAmount +
                '}';
    }
}