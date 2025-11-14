package com.pluralsight;
import com.pluralsight.drink.Drink;
import com.pluralsight.garlicKnots.GarlicKnot;
import com.pluralsight.pizza.Pizza;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private LocalDate orderDate;
    private LocalTime orderTime;
    private List <Pizza> pizzas;
    private List <Drink> drinks;
    private GarlicKnot garlicKnot;
    private double totalPrice;
    private double totalPricePizzas;
    private double totalPriceDrinks;
    private double totalPriceGarlicKnots;
    private double totalAmount;
    private String receiptFileName;

    public Order() {
        this.orderDate = LocalDate.now();
        this.orderTime = LocalTime.now();
        this.pizzas = new ArrayList<Pizza>();
        this.drinks = new ArrayList<Drink>();
        this.totalPrice = 0.00;
        this.receiptFileName = "";
    }

    public void addPizza(Pizza pizza){
        this.pizzas.add(pizza);
    }

    public void addDrink(Drink drink){
        this.drinks.add(drink);
    }

    public void addGarlicKnot(GarlicKnot garlicKnot){
        this.garlicKnot = garlicKnot;
    }

    public void calculateTotal() {

    }

    public void generateReceipt() {

    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }


    public boolean isValidOrder() {
        if (!pizzas.isEmpty()) {
            return true;
        } else if (!drinks.isEmpty() || garlicKnot != null) {
            return true;
        } else {
            System.out.println("You must order at least one pizza, drink, or garlic knots");
            return false;
        }
    }

    public GarlicKnot getGarlicKnot() {
        return garlicKnot;
    }

    public double getTotalPricePizzas() {
        return totalPricePizzas;
    }

    public double getTotalPriceDrinks() {
        return totalPriceDrinks;
    }

    public void setTotalPricePizzas(double totalPricePizzas) {
        this.totalPricePizzas = totalPricePizzas;
    }

    public void setTotalPriceDrinks(double totalPriceDrinks) {
        this.totalPriceDrinks = totalPriceDrinks;
    }

    public double getTotalPriceGarlicKnots() {
        return totalPriceGarlicKnots;
    }

    public void setTotalPriceGarlicKnots(double totalPriceGarlicKnots) {
        this.totalPriceGarlicKnots = totalPriceGarlicKnots;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
