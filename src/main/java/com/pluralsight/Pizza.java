package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String size;
    private String crustType;
    private Topping topping;
    private String sauce;
    boolean isStuffed;
    private List <Topping> toppings;
    private List<String> pizzaSauces;


    public Pizza(String size, String crustType, Topping topping,String sauce, boolean isStuffed) {
        this.size = size;
        this.crustType = crustType;
        this.topping = topping;
        this.sauce = sauce;
        this.isStuffed = isStuffed;
        this.toppings = new ArrayList<>();
        this.pizzaSauces = new ArrayList<>();

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
    public List<String> getPizzaSauces() {
        return pizzaSauces;
    }

    public Topping getTopping() {
        return topping;
    }
}


