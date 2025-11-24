package com.ooadassignment.bankingsystemtest.dao;

import com.ooadassignment.bankingsystemtest.model.Employee;
import com.ooadassignment.bankingsystemtest.model.SalaryPayment;
import com.ooadassignment.bankingsystemtest.util.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public List<Employee> fetchEmployees(int organizationId) throws SQLException {
        String sql = "SELECT employee_id, employee_name, employee_id_number, employee_position, base_salary, organization_id " +
                "FROM employees WHERE organization_id = ? ORDER BY employee_name";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, organizationId);
            try (ResultSet rs = stmt.executeQuery()) {
                List<Employee> employees = new ArrayList<>();
                while (rs.next()) {
                    employees.add(mapEmployee(rs));
                }
                return employees;
            }
        }
    }

    public Employee createEmployee(Employee employee) throws SQLException {
        String sql = "INSERT INTO employees (employee_name, employee_id_number, employee_position, base_salary, organization_id) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING employee_id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            bindEmployee(stmt, employee);
            stmt.setInt(5, employee.getOrganizationId());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    employee.setEmployeeId(rs.getInt(1));
                }
                return employee;
            }
        }
    }

    public void updateEmployee(Employee employee) throws SQLException {
        String sql = "UPDATE employees SET employee_name=?, employee_id_number=?, employee_position=?, base_salary=? WHERE employee_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            bindEmployee(stmt, employee);
            stmt.setInt(5, employee.getEmployeeId());
            stmt.executeUpdate();
        }
    }

    public void deleteEmployee(int employeeId) throws SQLException {
        String sql = "DELETE FROM employees WHERE employee_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            stmt.executeUpdate();
        }
    }

    public SalaryPayment creditSalary(int employeeId) throws SQLException {
        String fetchSql = "SELECT base_salary FROM employees WHERE employee_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement fetchStmt = conn.prepareStatement(fetchSql)) {
            fetchStmt.setInt(1, employeeId);
            try (ResultSet rs = fetchStmt.executeQuery()) {
                if (!rs.next()) {
                    throw new SQLException("Employee not found");
                }
                double amount = rs.getDouble("base_salary");
                LocalDateTime paymentTime = LocalDateTime.now();

                String insertSql = "INSERT INTO salary_payments (employee_id, amount, payment_date) VALUES (?, ?, ?) RETURNING payment_id";
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                    insertStmt.setInt(1, employeeId);
                    insertStmt.setDouble(2, amount);
                    insertStmt.setTimestamp(3, Timestamp.valueOf(paymentTime));
                    try (ResultSet paymentRs = insertStmt.executeQuery()) {
                        if (paymentRs.next()) {
                            return new SalaryPayment(paymentRs.getInt(1), employeeId, amount, paymentTime);
                        }
                    }
                }
                throw new SQLException("Failed to record salary payment");
            }
        }
    }

    private void bindEmployee(PreparedStatement stmt, Employee employee) throws SQLException {
        stmt.setString(1, employee.getEmployeeName());
        stmt.setString(2, employee.getEmployeeIdNumber());
        stmt.setString(3, employee.getEmployeePosition());
        stmt.setDouble(4, employee.getBaseSalary());
    }

    private Employee mapEmployee(ResultSet rs) throws SQLException {
        return new Employee(
                rs.getInt("employee_id"),
                rs.getString("employee_name"),
                rs.getString("employee_id_number"),
                rs.getString("employee_position"),
                rs.getDouble("base_salary"),
                rs.getInt("organization_id")
        );
    }
}
