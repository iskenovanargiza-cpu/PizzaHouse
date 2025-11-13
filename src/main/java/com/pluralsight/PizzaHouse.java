package com.pluralsight;

import com.pluralsight.drink.AddDrink;
import com.pluralsight.drink.Drink;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PizzaHouse {
    Scanner scanner = new Scanner(System.in);
    AddPizza addPizza = new AddPizza(scanner);
    Order order = new Order();

    public void display() {
        do {
            homeScreen();

            System.out.print("Please select your option here:");
            int userInput = scanner.nextInt();
            scanner.nextLine();

            switch (userInput) {
                case 1 -> orderScreen();
                case 0 -> System.exit(0);
                default -> {
                    System.out.println("Invalid number. Please try again...");
                }
            }
        } while (true);
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
            default -> {
                System.out.println("Invalid number. Returning to the home page screen...");
            }
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
        processCheckout();
    }

    public void processAddDrink() {
        String selectedDrink = selectDrinkFlavor();
        String selectedDrinkSize = selectDrinkSize();
        Drink drink = new Drink(selectedDrink, selectedDrinkSize);
        order.addDrink(drink);
        drink.calculateDrink(selectedDrinkSize);
    }

    public void processAddGarlicKnots() {
        System.out.println("Add garlic knots to your order ? 1)yes / 2)no");
        int inputAddKnots = scanner.nextInt();

        if (inputAddKnots == 1) {
            System.out.println("How many would you like to add?");
            int inputQuantity = scanner.nextInt();
            order.addGarlicKnots(inputQuantity);
        } else if (inputAddKnots == 2) {
            System.out.println("Would you like to process checkout? 1)yes / 2)no");
            int inputCheckout = scanner.nextInt();
            if (inputCheckout == 1) {
                processCheckout();

            } else if (inputCheckout == 2) {
                System.out.println("You are directed to the main menu...");
                orderScreen();
            }

        }

    }


    private void processCheckout() {
        System.out.println("Would you like to checkout? 1)yes / 2)no");
        int inputCheckout = scanner.nextInt();
        if (inputCheckout == 1) {
            if (!order.isValidOrder()) {
                System.out.println("You are directed to main menu");
                orderScreen();
            } else {
                printReceipt();
                confirmOrCancelOrder();
            }
        }
    }

    private void confirmOrCancelOrder() {
        System.out.println("1) Confirm / 2) Cancel");
        int inputConfirm = scanner.nextInt();
        if (inputConfirm == 1) {
            try {
                String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
                String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));
                Path dir = Paths.get("src/main/resources/receipts");
                Path filePath = dir.resolve(date + "-" + time);

                try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
                    writer.write(String.format("%s|%n", "Pizza"));
                    for (Pizza actualPizza : order.getPizzas()) {
                        String extraTopping = "extra " + actualPizza.getTopping().getName();
                        writer.write(String.format("%s|%s|%s|%s%n", actualPizza.getSize(), actualPizza.getCrustType(), actualPizza.getTopping().getName(), extraTopping));
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else if (inputConfirm == 2) {
            processCancelOrder();
        }
    }

    private void printReceipt(){
        System.out.println("===CHECKOUT===\nOrder summary:\ndate:" + LocalDate.now() + " " + LocalTime.now());
        System.out.println("-----------------------------------------------");
        double totalPriceForPizzas = 0;
        double totalPriceForDrinks = 0;

        if (!order.getPizzas().isEmpty()) {
            System.out.println("Pizza");

            for (Pizza pizza : order.getPizzas()) {
                System.out.println("Size: " + pizza.getSize());
                System.out.println("Crust type: " + pizza.getCrustType());
                System.out.println("Sauce: " + pizza.getSauce());
                System.out.println("Stuffed crust: " + (pizza.isStuffed() ? "yes" : "no"));
                System.out.println("Topping: " + pizza.getTopping().getCategory() + " \"" + pizza.getTopping().getName() + "\"");
                System.out.println("Added extra " + pizza.getTopping().getName() + " : " + (pizza.getTopping().isExtra() ? "yes" : "no"));
                System.out.println("Subtotal: $" + pizza.totalAmount(pizza));
                totalPriceForPizzas = totalPriceForPizzas + pizza.totalAmount(pizza);
            }
        }

        System.out.println("Total amount :" + " " + order.getPizzas().get(0).totalAmount(order.getPizzas().get(0)));
    }

    public void processCancelOrder() {
        clearAll();
        System.out.println("Your order has been canceled. Returning to home page...");
        homeScreen();
    }

    public void clearAll() {
        order.getPizzas().clear();
        drinks.clear();
        order.setGarlicKnots(0);
    }

}

