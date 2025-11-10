package com.pluralsight;

import java.util.HashMap;

public class Topping {
    private String name, category;
    private boolean isPremium;
    private boolean isExtra;
    private HashMap<String, Double> extraPrices;

    public Topping(String name, String category, boolean isPremium, boolean isExtra, HashMap<String, Double> extraPrices) {
        this.name = name;
        this.category = category;
        this.isPremium = isPremium;
        this.isExtra = isExtra;
        this.extraPrices = new HashMap<>();
    }

    public HashMap<String, Double> meatExtra() {
        extraPrices.put("Personal", 0.50);
        extraPrices.put("Medium", 1.00);
        extraPrices.put("Large", 1.50);
        return extraPrices;
    }
    public HashMap<String, Double> cheeseExtra() {
        extraPrices.put("Personal", 0.30);
        extraPrices.put("Medium", 0.60);
        extraPrices.put("Large", 0.90);
        return extraPrices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

    public boolean isExtra() {
        return isExtra;
    }

    public void setExtra(boolean extra) {
        isExtra = extra;
    }
}
