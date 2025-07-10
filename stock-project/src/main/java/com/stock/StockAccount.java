package com.stock;

import com.exceptions.StockException;

import java.util.SortedMap;
import java.util.TreeMap;

public class StockAccount {
    private final String name;
    private double balance;
    private final SortedMap<String, Stock> ownedStock;

    // Constructors

    public StockAccount(String name, double balance) {
        super();
        this.name = name;
        this.balance = balance;
        this.ownedStock = new TreeMap<>();
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

            String symbol = stock.getStockSymbol();
            if (ownedStock.containsKey(symbol)) {
                Stock existingStock = ownedStock.get(symbol);
                int totalShares = existingStock.getShares() + stock.getShares();
                existingStock.setShares(totalShares);
                ownedStock.put(symbol, existingStock);
            } else {
                ownedStock.put(symbol, stock);
            }
        }
    }

    public void sellStock(Stock proposedStock) throws StockException {
        String symbol = proposedStock.getStockSymbol();
        if (ownedStock.isEmpty()) throw new StockException("You do not own any stock to sell");

        if (!ownedStock.containsKey(symbol)) {
            throw new StockException("You don't own this stock: " + symbol);
        }

        Stock heldStock = ownedStock.get(symbol);
        int sharesOwned = heldStock.getShares();
        int sharesToSell = proposedStock.getShares();
        if (sharesOwned < sharesToSell) {
            throw new StockException("You cannot sell more stock than you own");
        } else {
            balance += sharesToSell * proposedStock.getStockPrice();
            int newShareAmount = heldStock.getShares() - sharesToSell;

            if (newShareAmount > 0) {
                proposedStock.setShares(newShareAmount);
                heldStock.setShares(newShareAmount);
            } else {
                ownedStock.remove(symbol);
            }
        }
    }
    public SortedMap<String, Stock> getHeldStocks() {
        return ownedStock;
    }

    public Stock getOwnedStock() {
        if (ownedStock.isEmpty()) {
            return null;
        }
        return ownedStock.values().iterator().next();
    }
}
