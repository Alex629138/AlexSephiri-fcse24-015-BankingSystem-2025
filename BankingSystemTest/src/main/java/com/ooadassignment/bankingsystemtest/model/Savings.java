package com.ooadassignment.bankingsystemtest.model;

import java.sql.Date;

public class Savings extends Account {

    private int savings_account_id;

    public Savings(int customer_id, String first_name, String last_name, String email, String phone_number, String address, Date date_of_birth, Date registration_date, int accountId, double balance, String accountType, String branch, int savings_account_id) {
        super(customer_id, first_name, last_name, email, phone_number, address, date_of_birth, registration_date, accountId, balance, accountType, branch);
        this.savings_account_id = savings_account_id;
    }

    public int getSavings_account_id() {
        return savings_account_id;
    }

    public void setSavings_account_id(int savings_account_id) {
        this.savings_account_id = savings_account_id;
    }
}