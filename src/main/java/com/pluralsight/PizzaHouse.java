package com.pluralsight;

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

    List<String> drinkFlavors = List.of(
            "Coke", "Sprite", "Fanta", "Water", "Iced Tea"
    );

    List<String> drinks = new ArrayList<>();

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

    private String selectDrinkFlavor() {
        String selectedDrink = "";
        System.out.println("===Please select drink===");
        drinks = drinkFlavors;
        for (int i = 0; i < drinks.size(); i++) {
            System.out.println((i + 1) + ")" + drinks.get(i));
        }
        int inputDrink = scanner.nextInt();
        scanner.nextLine();
        selectedDrink = drinks.get(inputDrink - 1);
        return selectedDrink;
    }

    private String selectDrinkSize() {
        String selectedDrinkSize = "";
        System.out.println("===Please select size===\n1)Small\n2)Medium\n3)Large");
        int inputDrinkSize = scanner.nextInt();
        scanner.nextLine();
        if (inputDrinkSize == 1) {
            selectedDrinkSize = "Small";
        } else if (inputDrinkSize == 2) {
            selectedDrinkSize = "Medium";
        } else if (inputDrinkSize == 3) {
            selectedDrinkSize = "Large";
        }
        return selectedDrinkSize;
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

        for (Pizza firstPizza : order.getPizzas()) {
            firstPizza.totalAmount(firstPizza);
            System.out.println("Size: " + firstPizza.getSize());
            System.out.println("Crust type: " + firstPizza.getCrustType());
            System.out.println("Sauce: " + firstPizza.getSauce());
            System.out.println("Stuffed crust: " + (firstPizza.isStuffed ? "yes" : "no"));
            System.out.println("Topping: " + firstPizza.getTopping().getCategory() + " \"" + firstPizza.getTopping().getName() + "\"");
            System.out.println("Added extra " + firstPizza.getTopping().getName() + " : " + (firstPizza.getTopping().isExtra() ? "yes" : "no"));
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

