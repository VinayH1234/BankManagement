package com.management;

import java.util.*;

class Account {
    private static int accountCounter = 1000;
    private int accountNumber;
    private String accountHolderName;
    private double balance;

    public Account(String accountHolderName, double initialDeposit) {
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
        this.accountNumber = ++accountCounter;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }
}

class Bank {
    private Map<Integer, Account> accounts = new HashMap<>();

    public void createAccount(String name, double initialDeposit) {
        Account newAccount = new Account(name, initialDeposit);
        accounts.put(newAccount.getAccountNumber(), newAccount);
        System.out.println("Account created successfully. Account Number: " + newAccount.getAccountNumber());
    }

    public void viewAccountDetails(int accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Account Holder: " + account.getAccountHolderName());
            System.out.println("Balance: " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    public void deposit(int accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    public void withdraw(int accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    public void transfer(int fromAccount, int toAccount, double amount) {
        Account sender = accounts.get(fromAccount);
        Account receiver = accounts.get(toAccount);

        if (sender != null && receiver != null) {
            if (amount > 0 && sender.getBalance() >= amount) {
                sender.withdraw(amount);
                receiver.deposit(amount);
                System.out.println("Transfer successful.");
            } else {
                System.out.println("Insufficient balance or invalid amount.");
            }
        } else {
            System.out.println("One or both accounts not found.");
        }
    }
}

public class BankManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();
        
        while (true) {
            System.out.println("\nBank Management System");
            System.out.println("1. Create Account");
            System.out.println("2. View Account Details");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Transfer");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter account holder name: ");
                    String name = scanner.next();
                    System.out.print("Enter initial deposit: ");
                    double deposit = scanner.nextDouble();
                    bank.createAccount(name, deposit);
                    break;
                case 2:
                    System.out.print("Enter account number: ");
                    int accountNumber = scanner.nextInt();
                    bank.viewAccountDetails(accountNumber);
                    break;
                case 3:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextInt();
                    System.out.print("Enter deposit amount: ");
                    deposit = scanner.nextDouble();
                    bank.deposit(accountNumber, deposit);
                    break;
                case 4:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextInt();
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawal = scanner.nextDouble();
                    bank.withdraw(accountNumber, withdrawal);
                    break;
                case 5:
                    System.out.print("Enter sender account number: ");
                    int fromAccount = scanner.nextInt();
                    System.out.print("Enter receiver account number: ");
                    int toAccount = scanner.nextInt();
                    System.out.print("Enter transfer amount: ");
                    double amount = scanner.nextDouble();
                    bank.transfer(fromAccount, toAccount, amount);
                    break;
                case 6:
                    System.out.println("Exiting... Thank you for using Bank Management System.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

