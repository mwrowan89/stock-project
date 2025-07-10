package com.stock;

import com.exceptions.StockException;

public class StockAccount {
    private final String name;
    private double balance;
    private Stock ownedStock;

    // Constructors

    public StockAccount(String name, double balance) {
        super();
        this.name = name;
        this.balance = balance;
    }
    public StockAccount(String name) {
        this(name, 1000);
    }

    // Getter and Setter
    public String getName() {
        return name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }


    // Methods
    public void buyStock(Stock stock) throws StockException {
        double total = stock.getShares() * stock.getStockPrice();
        try {
            if (stock.getShares() <= 0) {
                throw new StockException("You cannot buy 0 or less shares of stock");
            }
            if (stock.getStockPrice() <= 0) {
                throw new StockException("You cannot buy a stock that is less than or equal to 0");
            }
            if (stock.getStockSymbol() == null || stock.getStockSymbol().isBlank()) {
                throw new StockException("You must enter a valid stock symbol");
            }
        } catch (StockException e) {
            System.out.println(e.getMessage());
            return;
        }
        if(total > balance) {
            throw (new StockException("You do not have enough money to purchase this stock"));
        } else {
            setBalance(balance -= stock.getStockPrice() * stock.getShares());
            System.out.println("Thank you for your purchase of " + stock.getStockSymbol());
            System.out.println("Your new balance is " + String.format("%.2f",getBalance()));

            if (ownedStock != null && ownedStock.getStockSymbol().equalsIgnoreCase(stock.getStockSymbol())) {
                int totalShares = ownedStock.getShares() + stock.getShares();
                stock.setShares(totalShares);
                ownedStock = stock;
            } else {
                ownedStock = stock;
            }
        }
    }

    public void sellStock(Stock proposedStock) throws StockException {
        if (ownedStock == null) throw new StockException("You must enter a stock to sell this stock");

        if (proposedStock.getStockSymbol().equalsIgnoreCase(ownedStock.getStockSymbol())) {
            int shareAmount = ownedStock.getShares();
            int sellAmount = proposedStock.getShares();
            if (shareAmount < sellAmount) {
                throw new StockException("You cannot sell more stock than you own");
            } else {
                balance += sellAmount * proposedStock.getStockPrice();
                int newShareAmount = ownedStock.getShares() - sellAmount;

                if (newShareAmount > 0) {
                    proposedStock.setShares(newShareAmount);
                    ownedStock = proposedStock;
                } else {
                    ownedStock = null;
                }
            }
        } else {
            throw new StockException("You cannot sell more stock than you own");        }
    }
    public Stock getOwnedStock() {
        return ownedStock;
    }
}
