package com.stock;

public class Stock {
    private final String stockSymbol;
    private int shares;
    private final Double stockPrice;

    // Constructor
    public Stock(String stock, int shares, Double stockPrice) {
        this.stockSymbol = stock;
        this.shares = shares;
        this.stockPrice = stockPrice;
    }

    // Get and Set
    public String getStockSymbol() {
        return stockSymbol;
    }

    public int getShares() {
        return shares;
    }

    public Double getStockPrice() {
        return stockPrice;
    }


    public void setShares(int shares) {
        this.shares = shares;
    }


    // Methods
    @Override
    public String toString() {
        return " " +
                "stockSymbol: '" + stockSymbol + '\'' +
                ", shares = " + shares +
                ", stockPrice = " + stockPrice;
    }

}
