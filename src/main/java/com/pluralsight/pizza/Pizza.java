package com.pluralsight.pizza;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Pizza {
    private String size;
    private String crustType;
    private Topping topping;
    private String sauce;
    boolean isStuffed;
    private List<Topping> toppings;
    private double priceForExtraMeatTopping = 0.50;
    private double priceForExtraCheeseTopping = 0.30;

    private HashMap<String, Double> pizzaSizePricesMap = new HashMap<String, Double>() {{
        put("Personal", 8.50);
        put("Medium", 12.00);
        put("Large", 16.50);
    }};

    private HashMap<String, Double> pizzaToppingPricesMap = new HashMap<String, Double>() {{
        put("regular", 0.00);
        put("Premium meat category", 1.00);
        put("Premium cheese category", 0.75);
    }};

    public Pizza(String size, String crustType, Topping topping, String sauce, boolean isStuffed) {
        this.size = size;
        this.crustType = crustType;
        this.topping = topping;
        this.sauce = sauce;
        this.isStuffed = isStuffed;
        this.toppings = new ArrayList<>();
    }

    public double totalAmount(Pizza pizza) {
        double total = 0;
        if (pizza == null) {
            System.out.println("You don't have any pizza in the basket");
            return 0;
        } else {
            //size
            double priceForSize = pizzaSizePricesMap.get(pizza.getSize()); // size saved as string, for example large == 16.50
            double priceForTopping = pizzaToppingPricesMap.get(pizza.getTopping().getCategory());
            double priceForExtraTopping = 0;
            //main topping
            if (pizza.getSize().equals("Medium")) {
                priceForTopping = priceForTopping * 2;
            } else if (pizza.getSize().equals("Large")) {
                priceForTopping = priceForTopping * 3;
            }
            //extra topping
            if (pizza.getTopping().isExtra()) {
                if (pizza.getTopping().getCategory().equals("premiumMeat")) {
                    priceForExtraTopping = priceForExtraMeatTopping;
                    if (pizza.getSize().equals("Medium")) {
                        priceForExtraTopping = priceForExtraTopping + priceForExtraMeatTopping;
                    } else if (pizza.getSize().equals("Large")) {
                        priceForExtraTopping = priceForExtraTopping + (priceForExtraMeatTopping * 2);
                    }
                }
            } else if (pizza.getTopping().getCategory().equals("premiumCheese")) {
                priceForExtraTopping = priceForExtraCheeseTopping;
                if (pizza.getSize().equals(("Medium"))) {
                    priceForExtraTopping = priceForExtraTopping + priceForExtraCheeseTopping;
                } else if (pizza.getSize().equals("Large")) {
                    priceForExtraTopping = priceForExtraTopping + (priceForExtraCheeseTopping * 2);
                }
            }

            return priceForSize + priceForTopping + priceForExtraTopping;
        }
    }

    public String getSize() {
        return size;

    }

    public String getCrustType() {
        return crustType;

    }

    public boolean isStuffed() {
        return isStuffed;

    }

    public List<Topping> getToppings() {
        return toppings;

    }

    public String getSauce() {
        return sauce;
    }

    public Topping getTopping() {
        return topping;
    }
}
