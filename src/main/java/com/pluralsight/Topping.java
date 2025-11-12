package com.pluralsight;

import java.util.HashMap;
import java.util.List;

public class Topping {
    private String name, category;
    private boolean isPremium;
    private boolean isExtra;

    public Topping(String category, String name, boolean isPremium, boolean isExtra) {
        this.category = category;
        this.name = name;
        this.isPremium = isPremium;
        this.isExtra = isExtra;

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
