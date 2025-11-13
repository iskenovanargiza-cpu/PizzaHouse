package com.pluralsight.drink;

import java.util.HashMap;

public class Drink {
    private String size;
    private String flavor;
    private double price;
    private List<Drink> drinkFlavors;

    private HashMap<String, Double> pizzaSizePricesMap = new HashMap<String, Double>() {{
        put("Small", 2.00);
        put("Medium", 2.50);
        put("Large", 3.00);
    }};

    public Drink(String size, String flavor) {
        this.size = size;
        this.flavor = flavor;
        this.price = calculateDrink(size);
        this.drinkFlavors = new ArrayList<>();
    }

    public HashMap<String, Double> getPizzaSizePricesMap() {
        return pizzaSizePricesMap;
    }

    public String getSize() {
        return size;
    }

    public String getFlavor() {
        return flavor;
    }
}



