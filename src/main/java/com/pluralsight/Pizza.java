package com.pluralsight;

public class Pizza {
    private String size, crustType, topping;
    boolean isStuffed;

    public Pizza(String size, String type, String toppings, boolean isStuffed) {
        this.size = size;
        this.crustType= type;
        this.topping = toppings;
        this.isStuffed = isStuffed;
    }

    public double totalAmount(){

    }

    public String getSize() {
        return size;
    }

    public String getType() {
        return crustType;
    }

    public String getToppings() {
        return topping;
    }

    public boolean isStuffed() {
        return isStuffed;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setType(String type) {
        this.crustType = type;
    }

    public void setToppings(String toppings) {
        this.topping = toppings;
    }

    public void setStuffed(boolean stuffed) {
        isStuffed = stuffed;
    }
}

