package com.stock;

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
    public void buyStock(Stock stock) {
        double total = stock.getShares() * stock.getStockPrice();
        if(total > balance) {
            System.out.println("Sorry not enough money to purchase this stock");
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

    public void sellStock(Stock proposedStock) {
        if (ownedStock == null) {
            System.out.println("You don't own any stock to sell.");
            return;
        }

        if (proposedStock.getStockSymbol().equalsIgnoreCase(ownedStock.getStockSymbol())) {
            int shareAmount = ownedStock.getShares();
            int sellAmount = proposedStock.getShares();
            if (shareAmount < sellAmount) {
                System.out.println("Not enough stock to sell");
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
            System.out.println("Sorry you do not own any of that stock.");
        }
    }
    public Stock getOwnedStock() {
        return ownedStock;
    }
}
