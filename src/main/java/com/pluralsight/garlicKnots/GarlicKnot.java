package com.pluralsight.garlicKnots;

public class GarlicKnot {
    private double price = 1.5;
    private int count;

    public GarlicKnot(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
