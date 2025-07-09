package com.stock;

public class DividendStock extends Stock {
    private double dividendPaid;

    // Constructor
    public DividendStock(String stockSymbol, int shares, Double stockPrice) {
        super(stockSymbol, shares, stockPrice);
    }

    // Get and Set
    public double getDividendPaid() {
        return dividendPaid;
    }

    public void setDividendPaid(double dividendPaid) {
        this.dividendPaid = dividendPaid;
    }

    // Methods

}
