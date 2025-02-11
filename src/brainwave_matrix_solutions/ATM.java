package brainwave_matrix_solutions;

import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    private double balance;
    private String pin;
    private ArrayList<String> transactionHistory;

    public ATM(double initialBalance, String pin) {
        this.balance = initialBalance;
        this.pin = pin;
        this.transactionHistory = new ArrayList<>();
    }

    public boolean authenticate(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    public void checkBalance() {
        System.out.println("Your current balance is: ₹" + balance);
        transactionHistory.add("Checked Balance: ₹" + balance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited ₹" + amount);
            transactionHistory.add("Deposited: ₹" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            if (amount > 5000) {
                System.out.println("Withdrawal limit exceeded! Maximum per transaction: ₹5000.");
            } else {
                balance -= amount;
                System.out.println("Successfully withdrawn ₹" + amount);
                transactionHistory.add("Withdrew: ₹" + amount);
            }
        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }

    public void showTransactionHistory() {
        System.out.println("\nTransaction History:");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM(10000, "1234"); // Initial balance ₹10,000 and PIN "1234"

        System.out.print("Enter PIN to access ATM: ");
        String enteredPin = scanner.nextLine();

        if (!atm.authenticate(enteredPin)) {
            System.out.println("Incorrect PIN. Exiting...");
            return;
        }

        while (true) {
            System.out.println("\nATM Menu");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    atm.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ₹");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ₹");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;
                case 4:
                    atm.showTransactionHistory();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}

