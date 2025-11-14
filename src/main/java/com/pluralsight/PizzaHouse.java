package com.pluralsight;

import com.pluralsight.drink.AddDrink;
import com.pluralsight.drink.Drink;
import com.pluralsight.garlicKnots.AddGarlicKnots;
import com.pluralsight.garlicKnots.GarlicKnot;
import com.pluralsight.pizza.AddPizza;
import com.pluralsight.pizza.Pizza;
import com.pluralsight.pizza.Topping;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PizzaHouse {
    Scanner scanner = new Scanner(System.in);
    AddPizza addPizza = new AddPizza(scanner);
    AddDrink addDrink = new AddDrink(scanner);
    AddGarlicKnots addGarlicKnots = new AddGarlicKnots(scanner);
    Order order = new Order();

    public void display() {
        boolean running = true;
        while (running) {
            homeScreen();

            System.out.print("Please select your option here: ");
            int userInput = scanner.nextInt();
            scanner.nextLine();

            switch (userInput) {
                case 1 -> orderScreen();
                case 0 -> running = false;
                default -> System.out.println("Invalid number. Please try again...");
            }
        }
    }

    public void homeScreen() {
        System.out.println("===Welcome to our Pizza House===\nPlease select option\n1)New order\n0)Exit");
    }

    public void orderScreen() {
        System.out.println("1) Add Pizza\n2) Add Drink\n3) Add Garlic Knots\n4) Checkout\n0) Cancel Order");
        int userInput = scanner.nextInt();
        scanner.nextLine();

        switch (userInput) {
            case 1 -> processAddPizza();
            case 2 -> processAddDrink();
            case 3 -> processAddGarlicKnots();
            case 4 -> processCheckout();
            case 0 -> processCancelOrder();
            default -> System.out.println("Invalid number. Returning to the home page screen...");
        }
    }

    public void processAddPizza() {
        boolean isPremium;
        boolean isExtra;
        boolean isStuffed;
        String selectedType = addPizza.selectAndSetCrustType();
        String selectedSize = addPizza.selectSize();
        String selectedToppingCategory = addPizza.selectToppingCategory();
        isPremium = addPizza.setPremiumCategory(selectedToppingCategory);
        String selectedToppingName = addPizza.selectTopping();
        isExtra = addPizza.addExtraTopping(selectedToppingName);
        String selectedSauceName = addPizza.selectSauce();
        isStuffed = addPizza.addStuffed();
        Topping topping = new Topping(selectedToppingCategory, selectedToppingName, isPremium, isExtra);
        Pizza pizza = new Pizza(selectedSize, selectedType, topping, selectedSauceName, isStuffed);
        order.addPizza(pizza);
        addAnotherProduct();
    }

    public void processAddDrink() {
        String selectedDrinkSize = addDrink.selectDrinkSize();
        String selectedDrink = addDrink.selectDrinkFlavor();
        Drink drink = new Drink(selectedDrinkSize, selectedDrink);
        order.addDrink(drink);
        addAnotherProduct();
    }

    public void processAddGarlicKnots() {
        int countOfAddedNotes = addGarlicKnots.getCountOfAddedGarlicKnots();
        if (countOfAddedNotes > 0) {
            order.addGarlicKnot(new GarlicKnot(countOfAddedNotes));
            addAnotherProduct();
        }
    }

    private void addAnotherProduct() {
        System.out.println("Would you like to add another item? 1)yes | 2)checkout | 3) cancel order");
        int userInput = scanner.nextInt();
        if (userInput == 1) {
            orderScreen();
        } else if (userInput == 2) {
            processCheckout();
        } else if (userInput == 3) {
            clearAll();
            System.out.println("Your order was successfully cancelled");
            System.exit(0);
        }
    }

    private void processCheckout() {
        if (!order.isValidOrder()) {
            System.out.println("You are directed to main menu");
            orderScreen();
        } else {
            printReceipt();
            confirmOrCancelOrder();
        }
    }

    private void printReceipt() {
        System.out.println("===CHECKOUT===\nOrder summary:\ndate:" + LocalDate.now() + " " + LocalTime.now());
        System.out.println("-----------------------------------------------");
        double totalPriceForPizzas = 0;
        double totalPriceForDrinks = 0;

        if (!order.getPizzas().isEmpty()) {
            System.out.println("Pizzas");
            for (int i = 0; i < order.getPizzas().size(); i++) {
                Pizza pizza = order.getPizzas().get(i);
                System.out.println("---Pizza " + (i + 1) + "---");
                System.out.println("Size: " + pizza.getSize());
                System.out.println("Crust type: " + pizza.getCrustType());
                System.out.println("Sauce: " + pizza.getSauce());
                System.out.println("Stuffed crust: " + (pizza.isStuffed() ? "yes" : "no"));
                System.out.println("Topping: " + pizza.getTopping().getCategory() + " \"" + pizza.getTopping().getName() + "\"");
                System.out.println("Added extra " + pizza.getTopping().getName() + " : " + (pizza.getTopping().isExtra() ? "yes" : "no"));
                System.out.println("Subtotal: $" + pizza.totalAmount(pizza));
                totalPriceForPizzas = totalPriceForPizzas + pizza.totalAmount(pizza);
            }
            order.setTotalPricePizzas(totalPriceForPizzas);
        }

        if (!order.getDrinks().isEmpty()) {
            System.out.println("Drinks");
            for (Drink drink : order.getDrinks()) {
                System.out.println("-" + drink.getSize() + " " + drink.getFlavor() + ": $" + drink.getPizzaSizePricesMap().get(drink.getSize()));
                totalPriceForDrinks = totalPriceForDrinks + drink.getPizzaSizePricesMap().get(drink.getSize());
            }
            order.setTotalPriceDrinks(totalPriceForDrinks);
        }

        if (order.getGarlicKnot() != null){
            System.out.println("Garlic Knot");
            System.out.println("Count: " + order.getGarlicKnot().getCount());
            order.setTotalPriceGarlicKnots(order.getGarlicKnot().getPrice() * order.getGarlicKnot().getCount());
            System.out.println("Subtotal: " + "$" + order.getTotalPriceGarlicKnots());
        }

        order.setTotalAmount(totalPriceForPizzas + totalPriceForDrinks + order.getTotalPriceGarlicKnots());
        System.out.println("Total amount: " + (totalPriceForPizzas + totalPriceForDrinks + order.getTotalPriceGarlicKnots()));
    }

    private void confirmOrCancelOrder() {
        System.out.println("Confirm your order, or cancel or go back to main menu");
        System.out.println("1) confirm | 2) cancel | 3) go back to main menu ");
        int inputConfirm = scanner.nextInt();
        if (inputConfirm == 1) {
            try {
                String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
                String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));
                Path dir = Paths.get("src/main/resources/receipts");
                Path filePath = dir.resolve(date + "-" + time);
                try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
                    if (!order.getPizzas().isEmpty()) {
                        writer.write(String.format("%s:%n", "Pizzas"));
                        for (Pizza actualPizza : order.getPizzas()) {
                            String extraTopping = "extra " + actualPizza.getTopping().getName();
                            writer.write(String.format("%s|%s|%s|%s%n", actualPizza.getSize(), actualPizza.getCrustType(), actualPizza.getTopping().getName(), extraTopping));
                        }
                        writer.write(String.format("%s:%s|%n", "Subtotal ", order.getTotalPricePizzas()));
                    }

                    if (!order.getDrinks().isEmpty()) {
                        writer.write(String.format("%s:%n", "Drinks"));
                        for (Drink actualDrink : order.getDrinks()) {
                            writer.write(String.format("%s|%s|%s|%s%n", actualDrink.getSize(), actualDrink.getFlavor(), "Subtotal", order.getTotalPriceDrinks()));
                        }

                    }

                    if (order.getGarlicKnot() != null)  {
                        writer.write(String.format("%s:%n", "Garlic Knots"));
                        writer.write(String.format("%s|%s%n", "Count: ", order.getGarlicKnot().getCount()));
                        writer.write(String.format("%s:%s|%n", "Subtotal", order.getTotalPriceGarlicKnots()));
                    }
                    writer.write(String.format("%s:%s%n", "Total amount: ", order.getTotalAmount() ));
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Your order was completed, we are working on your order. See you again!");
            System.exit(0);
        } else if (inputConfirm == 2) {
            clearAll();
            System.out.println("Your order was successfully cancelled");
            System.exit(0);
        } else if (inputConfirm == 3) {
            return;
        }
    }

    public void processCancelOrder() {
        clearAll();
        System.out.println("Your order has been canceled. Returning to home page...");
        homeScreen();
    }

    public void clearAll() {
        order.getPizzas().clear();
        order.getDrinks().clear();
        order.getGarlicKnot().setCount(0);
    }
}

