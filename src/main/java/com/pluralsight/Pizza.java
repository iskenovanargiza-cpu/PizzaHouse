package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String size, crustType;
    private List <Topping> toppings;
    boolean isStuffed;

    public Pizza(String size, String type, String toppings, boolean isStuffed) {
        this.size = size;
        this.crustType= type;
        this.toppings = new ArrayList<>();
        this.isStuffed = isStuffed;
    }

    public double totalAmount(){
        double total = 0;
        if (size.contains("Personal") || (size.contains("8"))){
            total += 8.50;
        }
        if (size.contains("Medium") || (size.contains("12"))){
            total += 12.00;
        }
        if (size.contains("Large") || (size.contains("16"))){
            total += 16.50;
        } for (Topping topping : toppings) {
            if (topping.isPremium() && size.contains("Personal") || (size.contains("8"))) {
                total += 8.50 + 1.00;
                if(topping.isExtra()){
                    topping.
                }
            } else if (topping.isPremium() && size.contains("Medium") || (size.contains("12"))) {
                total += 12.00 + 2.00;
            } else if (topping.isPremium() && size.contains("Large") || (size.contains("16"))) {
                total += 16.50 + 3.00;
            }

        }




        return total;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCrustType() {
        return crustType;
    }

    public void setCrustType(String crustType) {
        this.crustType = crustType;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public boolean isStuffed() {
        return isStuffed;
    }

    public void setStuffed(boolean stuffed) {
        isStuffed = stuffed;
    }
}


