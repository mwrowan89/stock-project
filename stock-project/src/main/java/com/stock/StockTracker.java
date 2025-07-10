package com.stock;
import com.exceptions.InputError;
import com.exceptions.StockException;

import java.util.Scanner;

public class StockTracker {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws InputError {
        printIntro();
        StockAccount account = collectAccountInfo();

        printAccountSummary(account);

        userSelection(account);

        //lAST line in main
        input.close();
    }

    private static void userSelection(StockAccount account) {
        int choice = 0;

        while (choice != 3) {
            System.out.println("What would you like to do today?");
            System.out.println("1. Buy Stock");
            System.out.println("2. Sell Stock");
            System.out.println("3. Exit");
            System.out.print("Please enter 1, 2, or 3: ");

            boolean validInput = false;
            while (!validInput) {
                try {
                    choice = input.nextInt();

                    if (choice < 1 || choice > 3) {
                        System.out.print("Invalid choice. Please enter 1, 2, or 3: ");
                    } else {
                        validInput = true;
                    }
                } catch (java.util.InputMismatchException e) {
                    input.next(); // Clear the invalid input
                    System.out.print("Invalid input. Please enter a number (1, 2, or 3): ");
                }
            }

            if (choice == 1) {
                Stock purchaseStock = collectStockInfo();
                try {
                    account.buyStock(purchaseStock);
                } catch (StockException e) {
                    System.out.println("Error occurred while buying stock: " + e.getMessage());
                }
                printAccountSummary(account);
            } else if (choice == 2) {
                if(account.getOwnedStock() == null) {
                    System.out.println("You do not own any stock to sell.");
                    System.out.println();
                    continue;
                }
                Stock sellStock = collectStockInfo();
                try {
                    account.sellStock(sellStock);
                } catch (StockException e) {
                    System.out.println("Error occurred while selling stock: " + e.getMessage());
                }
                printAccountSummary(account);
            }
        }

        System.out.println("Thank you for using the Stock Tracker. Goodbye!");
    }
    private static Stock collectStockInfo() {
        System.out.print("Please enter the name of the stock: ");
        String stockName = input.next();
        System.out.print("Please enter the number of stocks: ");
        int stockAmount = input.nextInt();
        System.out.print("Please enter the price: ");
        Double stockPrice = input.nextDouble();

        System.out.print("Is this a dividend stock? (y/n): ");
        String isDividendStock = input.next();

        if (isDividendStock.equalsIgnoreCase("y")) {
            System.out.print("Please enter the dividend amount: ");
            double dividendAmount = input.nextDouble();
            DividendStock stock = new DividendStock(stockName, stockAmount, stockPrice);
            stock.setDividendPaid(dividendAmount);
            return stock;
        } else {
            return new Stock(stockName, stockAmount, stockPrice);
        }
    }

    private static StockAccount collectAccountInfo() {
        System.out.print("Please enter your full name: ");
        String name = input.nextLine();
        System.out.print("Enter the initial balance of your account: ");
        double balance = input.nextDouble();

        if(balance < 0) {
            System.out.println("Your balance is below 0");
            StockAccount account = new StockAccount(name);
            System.out.println("Account balance has been initialized with " + account.getBalance());
            return account;
        } else {
            return new StockAccount(name, balance);
        }
    }

    private static void printAccountSummary(StockAccount account) {
        System.out.println();
        System.out.println("Account details:");
        System.out.println("Name: " + account.getName());
        System.out.println("Account Balance: " + String.format("%.2f",account.getBalance()));
        Stock ownedStock = account.getOwnedStock();
        if (ownedStock == null) {
            System.out.println("You do not own any stock. Sorry");
        } else {
            System.out.println("You own " + ownedStock.toString());
        }
        System.out.println();
    }

    private static void printIntro() {
        System.out.println("*******************************");
        System.out.println(" Welcome to the Stock Tracker!");
        System.out.println("*******************************");
        System.out.println();
    }
}