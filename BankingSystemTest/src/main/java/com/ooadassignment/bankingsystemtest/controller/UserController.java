package com.ooadassignment.bankingsystemtest.controller;

import com.ooadassignment.bankingsystemtest.dao.SavingsDAO;
import com.ooadassignment.bankingsystemtest.dao.InvestmentDAO;
import com.ooadassignment.bankingsystemtest.model.Savings;
import com.ooadassignment.bankingsystemtest.model.Investment;
import com.ooadassignment.bankingsystemtest.view.UserView;
import com.ooadassignment.bankingsystemtest.util.ValidationUtil;

import java.sql.SQLException;

public class UserController {
    private UserView userView;
    private SavingsDAO savingsDAO;
    private InvestmentDAO investmentDAO;

    public UserController() {
        this.userView = new UserView();
        this.savingsDAO = new SavingsDAO();
        this.investmentDAO = new InvestmentDAO();
    }

    public void handleMenu(int customerId) {
        int choice;
        do {
            userView.showMenu();
            choice = userView.getMenuChoice();

            switch (choice) {
                case 1:
                    handleAccountCreation(customerId);
                    break;
                case 8:
                    System.out.println("Thank you for using our banking system!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1 to create an account or 8 to exit.");
                    break;
            }
        } while (choice != 8);
    }

    public void handleAccountCreation(int customerId) {
        int choice = userView.getAccountCreationChoice();

        switch (choice) {
            case 1:
                createSavingsAccount(customerId);
                break;
            case 2:
                createInvestmentAccount(customerId);
                break;
            case 3:
                return;
            default:
                userView.showAccountCreationFailure("Invalid choice. Please select 1 for Savings or 2 for Investment.");
                break;
        }
    }

    private void createSavingsAccount(int customerId) {
        try {
            double initialDeposit = userView.getInitialDeposit();

            // Validate deposit amount using business rules
            ValidationUtil.ValidationResult validation = ValidationUtil.validateDepositAmount(initialDeposit, "Savings");
            if (!validation.isValid()) {
                userView.showAccountCreationFailure(validation.getMessage());
                return;
            }

            Savings savings = new Savings();
            savings.setCustomer_id(customerId);
            savings.setBalance(initialDeposit);
            savings.setAccountType("Savings");

            savingsDAO.createSavingsAccount(savings);
            userView.showAccountCreationSuccess("Savings");

        } catch (SQLException e) {
            userView.showAccountCreationFailure("Database error: " + e.getMessage());
        } catch (Exception e) {
            userView.showAccountCreationFailure("Error: " + e.getMessage());
        }
    }

    private void createInvestmentAccount(int customerId) {
        try {
            double initialDeposit = userView.getInitialDeposit();

            // Validate deposit amount using business rules
            ValidationUtil.ValidationResult validation = ValidationUtil.validateDepositAmount(initialDeposit, "Investment");
            if (!validation.isValid()) {
                userView.showAccountCreationFailure(validation.getMessage());
                return;
            }

            Investment investment = new Investment();
            investment.setCustomer_id(customerId);
            investment.setBalance(initialDeposit);
            investment.setAccountType("Investment");

            investmentDAO.createInvestmentAccount(investment);
            userView.showAccountCreationSuccess("Investment");

        } catch (SQLException e) {
            userView.showAccountCreationFailure("Database error: " + e.getMessage());
        } catch (Exception e) {
            userView.showAccountCreationFailure("Error: " + e.getMessage());
        }
    }
}
