package com.ooadassignment.bankingsystemtest.view;

import java.util.Scanner;

public class UserView {
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu(){
        System.out.println("\n---Banking System---");
        System.out.println("1: Create Account");
        System.out.println("2: Create New user");
        System.out.println("8: Exit");
        System.out.println("Choose an option: ");
    }

    public int getAccountCreationChoice() {
        System.out.println("\n---Account Creation---");
        System.out.println("1: Create Savings Account");
        System.out.println("2: Create Investment Account");
        System.out.println("3: Back to Main Menu");
        System.out.print("Choose account type: ");
        return scanner.nextInt();
    }

    public double getInitialDeposit() {
        System.out.print("Enter initial deposit amount: $");
        return scanner.nextDouble();
    }

    public void showAccountCreationSuccess(String accountType) {
        System.out.println(accountType + " account created successfully!");
    }

    public void showAccountCreationFailure(String reason) {
        System.out.println("Account creation failed: " + reason);
    }

    public void showMinimumDepositError() {
        System.out.println("Investment account requires a minimum deposit of $500.00");
    }

    public int getMenuChoice() {
        return scanner.nextInt();
    }
}
