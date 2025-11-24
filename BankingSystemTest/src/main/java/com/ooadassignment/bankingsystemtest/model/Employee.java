package com.ooadassignment.bankingsystemtest.model;

public class Employee {

    private int employeeId;
    private String employeeName;
    private String employeeIdNumber;
    private String employeePosition;
    private double baseSalary;
    private int organizationId;

    public Employee() {
    }

    public Employee(int employeeId, String employeeName, String employeeIdNumber,
                    String employeePosition, double baseSalary, int organizationId) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeIdNumber = employeeIdNumber;
        this.employeePosition = employeePosition;
        this.baseSalary = baseSalary;
        this.organizationId = organizationId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeIdNumber() {
        return employeeIdNumber;
    }

    public void setEmployeeIdNumber(String employeeIdNumber) {
        this.employeeIdNumber = employeeIdNumber;
    }

    public String getEmployeePosition() {
        return employeePosition;
    }

    public void setEmployeePosition(String employeePosition) {
        this.employeePosition = employeePosition;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }
}
