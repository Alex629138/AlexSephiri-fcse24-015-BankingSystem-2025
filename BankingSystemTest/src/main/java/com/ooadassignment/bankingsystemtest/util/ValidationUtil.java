package com.ooadassignment.bankingsystemtest.util;

public class ValidationUtil {

    private static final double MINIMUM_INVESTMENT_DEPOSIT = 500.0;
    private static final double MINIMUM_SAVINGS_DEPOSIT = 0.0;

    public static ValidationResult validateDepositAmount(double amount, String accountType) {
        if (amount < 0) {
            return new ValidationResult(false, "Deposit amount cannot be negative.");
        }

        if ("Investment".equalsIgnoreCase(accountType) && amount < MINIMUM_INVESTMENT_DEPOSIT) {
            return new ValidationResult(false,
                String.format("Investment account requires a minimum deposit of $%.2f", MINIMUM_INVESTMENT_DEPOSIT));
        }

        if ("Savings".equalsIgnoreCase(accountType) && amount < MINIMUM_SAVINGS_DEPOSIT) {
            return new ValidationResult(false, "Savings account deposit cannot be negative.");
        }

        return new ValidationResult(true, "Valid deposit amount.");
    }

    public static boolean isValidAccountType(String accountType) {
        return "Savings".equalsIgnoreCase(accountType) || "Investment".equalsIgnoreCase(accountType);
    }

    public static class ValidationResult {
        private final boolean valid;
        private final String message;

        public ValidationResult(boolean valid, String message) {
            this.valid = valid;
            this.message = message;
        }

        public boolean isValid() {
            return valid;
        }

        public String getMessage() {
            return message;
        }
    }
}
