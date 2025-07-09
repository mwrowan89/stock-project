package com.stock.lab;
import java.util.Scanner;
import com.stock.lab.Stock;

public class StockTracker {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        printIntro();
        StockAccount account = collectAccountInfo();

        printAccountSummary(account);

        userSelection(account);

        printAccountSummary(account);

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
            choice = input.nextInt();

            while (choice < 1 || choice > 3) {
                System.out.print("Invalid choice. Please enter 1, 2, or 3: ");
                choice = input.nextInt();
            }

            if (choice == 1) {
                Stock purchaseStock = collectStockInfo();
                account.buyStock(purchaseStock);
                printAccountSummary(account);
            } else if (choice == 2) {
                if(account.getOwnedStock() == null) {
                    System.out.println("You do not own any stock to sell.");
                    System.out.println();
                    continue;
                }
                Stock sellStock = collectStockInfo();
                account.sellStock(sellStock);
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

        return new Stock(stockName, stockAmount, stockPrice);
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
        System.out.println("Account Balance: " + account.getBalance());
        Stock ownedStock = account.getOwnedStock();
        if (ownedStock == null) {
            System.out.println("You do not own any stock. Sorry");
        } else {
            System.out.println("You own " + ownedStock.getShares()
                    + " shares of " + ownedStock.getStockSymbol() );
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
