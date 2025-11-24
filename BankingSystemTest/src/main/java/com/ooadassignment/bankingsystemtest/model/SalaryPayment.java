package com.ooadassignment.bankingsystemtest.model;

import java.time.LocalDateTime;

public class SalaryPayment {

    private int paymentId;
    private int employeeId;
    private double amount;
    private LocalDateTime paymentDate;

    public SalaryPayment(int paymentId, int employeeId, double amount, LocalDateTime paymentDate) {
        this.paymentId = paymentId;
        this.employeeId = employeeId;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }
}
