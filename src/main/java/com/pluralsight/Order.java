package com.pluralsight;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private LocalDate orderDate;
    private LocalTime orderTime;
    private List <Pizza> pizzas;
    private List <Drink> drinks;
    private int garlicKnots;
    private double totalPrice;
    private String receiptFileName;

    public Order() {
        this.orderDate = LocalDate.now();
        this.orderTime = LocalTime.now();
        this.pizzas = new ArrayList<Pizza>();
        this.drinks = new ArrayList<Drink>();
        this.garlicKnots = 0;
        this.totalPrice = 0.00;
        this.receiptFileName = "";
    }

    public void addPizza(Pizza pizza){
        this.pizzas.add(pizza);
    }

    public void addDrink(Drink drink){
        this.drinks.add(drink);
    }

    public double addGarlicKnots(int garlicKnots){
       return garlicKnots * 1.50;
    }

    public void calculateTotal() {

    }

    public void generateReceipt() {

    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public int getGarlicKnots() {
        return garlicKnots;
    }

    public boolean isValidOrder() {
        if(pizzas.isEmpty() && drinks.isEmpty() && garlicKnots == 0) {
            System.out.println("You must order at least one pizza, drink, or garlic knots");
            return false;
        }
        return true;
    }

    public void setGarlicKnots(int garlicKnots) {
        this.garlicKnots = garlicKnots;
    }
}
