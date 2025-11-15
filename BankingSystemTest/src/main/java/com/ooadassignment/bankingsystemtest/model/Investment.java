package com.ooadassignment.bankingsystemtest.model;

import java.sql.Date;

public class Investment extends Account {

    private int investment_account_id;

    public Investment(int customer_id, String first_name, String last_name, String email, String phone_number, String address, Date date_of_birth, Date registration_date, int accountId, double balance, String accountType, String branch, int investment_account_id) {
        super(customer_id, first_name, last_name, email, phone_number, address, date_of_birth, registration_date, accountId, balance, accountType, branch);
        this.investment_account_id = investment_account_id;
    }

    public int getInvestment_account_id() {
        return investment_account_id;
    }

    public void setInvestment_account_id(int investment_account_id) {
        this.investment_account_id = investment_account_id;
    }
}