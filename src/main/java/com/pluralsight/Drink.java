package com.pluralsight;

public class Drink {
    private String size;
    private String flavor;
    private double price;

    public Drink(String size, String flavor, double price) {
        this.size = size;
        this.flavor = flavor;
        this.price = price;
    }

    public double calculateDrink(String size) {
        if (size.equals("Small")) {
            return 2.00;
        } else if (size.equals("Medium")) {
            return 2.50;
        } else if (size.equals("Large")) {
            return 3.00;
        }

        return 0;
    }

    public String getSize() {
        return size;
    }

    public String getFlavor() {
        return flavor;
    }

    public double getPrice() {
        return price;
    }
}



