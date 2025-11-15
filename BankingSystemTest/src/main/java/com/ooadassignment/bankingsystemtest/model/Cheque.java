package com.ooadassignment.bankingsystemtest.model;

import java.sql.Date;

public class Cheque extends Account {
    private int chequeAccountId;
    private double salaryAmount;
    private String companyName, companyAddress, position;

    public Cheque() {}

    public Cheque(int customer_id, String first_name, String last_name, String email, String phone_number, String address, Date date_of_birth, Date registration_date, int accountId, double balance, String accountType, String branch, int chequeAccountId, double salaryAmount, String companyName, String companyAddress, String position) {
        super(customer_id, first_name, last_name, email, phone_number, address, date_of_birth, registration_date, accountId, balance, accountType, branch);
        this.chequeAccountId = chequeAccountId;
        this.salaryAmount = salaryAmount;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.position = position;
    }

    public int getChequeAccountId() {
        return chequeAccountId;
    }

    public void setChequeAccountId(int chequeAccountId) {
        this.chequeAccountId = chequeAccountId;
    }

    public double getSalaryAmount() {
        return salaryAmount;
    }

    public void setSalaryAmount(double salaryAmount) {
        this.salaryAmount = salaryAmount;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Cheque{" +
                "chequeAccountId=" + chequeAccountId +
                ", salaryAmount=" + salaryAmount +
                ", companyName='" + companyName + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}