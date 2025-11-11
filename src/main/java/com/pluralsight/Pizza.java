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
        }
        for (Topping topping : toppings) {
            if (topping.isPremium() && size.contains("Personal")) {
                total += 1.00;
                if (topping.isExtra()) {
                    if(topping.equals("Meat")) {
                        total += 0.50;
                    } else if (topping.equals("Cheese")) {
                        total += 0.30;
                    }
                }
            } else if (topping.isPremium() && size.contains("Medium")) {
                total += 2.00;
                if (topping.equals("Meat")) {
                    total += 1.00;
                } else if (topping.equals("Cheese")) {
                    total += 0.60;
            } else if (topping.isPremium() && size.contains("Large")) {
                total += 3.00;
                    if (topping.equals("Meat")) {
                        total += 1.50;
                    } else if (topping.equals("Cheese")) {
                        total += 0.90;
                    }
                }
            }
        } return total;
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


