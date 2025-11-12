package com.pluralsight;

import java.util.ArrayList;
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

    public String getCategory() {
        return category;

    }

    public boolean isPremium() {
        return isPremium;

    }

    public boolean isExtra() {
        return isExtra;


    }


}
