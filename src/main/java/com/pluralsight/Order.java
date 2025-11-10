package com.pluralsight;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Order {
    private LocalDate orderDate;
    private LocalTime orderTime;
    private List <Pizza> pizzas;
    private List <Drink> drinks;
    private int garlicKnots;
    private double totalPrice;
    private String receiptFileName;

    public Order(LocalDate orderDate, LocalTime orderTime, List<Pizza> pizzas, List<Drink> drinks, int garlicKnots, double totalPrice, String receiptFileName) {
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.pizzas = pizzas;
        this.drinks = drinks;
        this.garlicKnots = garlicKnots;
        this.totalPrice = totalPrice;
        this.receiptFileName = receiptFileName;
    }

    public void addPizza(Pizza pizza){

    }

    public void addDrink(Drink drink){

    }

    public void addGarlicKnots(int garlicKnots){

    }

    public void calculateTotal() {

    }

    public void generateReceipt() {

    }

}
