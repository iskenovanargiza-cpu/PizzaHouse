package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Interface {
    Scanner scanner = new Scanner(System.in);
    Order order = new Order();
    List<String> regularToppings = List.of(
            "Onions", "Mushrooms", "Bell Peppers", "Olives",
            "Tomatoes", "Spinach", "Basil", "Pineapple", "Anchovies"
    );

    List<String> premiumToppings = List.of(
            "Pepperoni", "Sausage", "Ham", "Bacon", "Chicken", "Meatball",
            "Mozzarella", "Parmesan", "Ricotta", "Goat Cheese", "Buffalo"
    );

    List<String> toppings = new ArrayList<>();

    public Interface() {
        System.out.println("!Program starts here!");
    }

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
//            case 2 -> processAddDrink();
//            case 3 -> processAddGarlicKnots();
//            case 4 -> processCheckout();
//            case 0 -> processCancelOrder;
            default -> {
                System.out.println("Invalid number. Returning to the home page screen...");
            }
        }
    }

    public void processAddPizza() {
        boolean isPremium;
        boolean isExtra;
        boolean isStuffed;
        String selectedType = selectAndSetCrustType();
        String selectedSize = selectSize();
        String selectedToppingCategory = selectToppingCategory();
        isPremium = setPremiumCategory(selectedToppingCategory);
        String selectedToppingName = selectTopping();
        isExtra = addExtraTopping(selectedToppingName);
        isStuffed = addStuffed();
        Topping topping = new Topping(selectedToppingCategory, selectedToppingName, isPremium, isExtra);
        Pizza pizza = new Pizza(selectedSize, selectedType, topping, isStuffed);
        order.addPizza(pizza);

        //checkout
        System.out.println("Would you like to checkout? 1)yes / 2)no");
        int inputCheckout = scanner.nextInt();
        if(inputCheckout == 1) {
            System.out.println(order
                    .getPizzas()
                    .get(0)
                    .getTopping()
                    .getCategory());
            System.out.println(order.getPizzas().get(0).getTopping().getName());
        }
    }

    private String selectAndSetCrustType() {
        String selectedType = "";
        System.out.println("===Please select pizza crust===\n 1)Thin\n2)Regular\n3)Thick\n4)Cauliflower");
        int inputCrustChoice = scanner.nextInt();
        if (inputCrustChoice == 1) {
            selectedType = "Thin";
        } else if (inputCrustChoice == 2) {
            selectedType = "Regular";
        } else if (inputCrustChoice == 3) {
            selectedType = "Thick";
        } else if (inputCrustChoice == 4) {
            selectedType = "Cauliflower";
        }
        return selectedType;
    }

    private String selectSize() {
        String selectedSize = "";
        System.out.println("===Please select pizza Size===\n1)Personal 8\n2)Medium 12\n3) Large 16");
        int inputSize = scanner.nextInt();
        if (inputSize == 1) {
            selectedSize = "Personal";
        } else if (inputSize == 2) {
            selectedSize = "Medium";
        } else if (inputSize == 3) {
            selectedSize = "Large";
        }
        return selectedSize;
    }

    private String selectToppingCategory() {
        String selectedToppingCategory = "";
        System.out.println("===Please select topping category===\n1)Regular Toppings\n2)Premium Toppings(extra cost)");
        int inputToppingCategory = scanner.nextInt();

        if (inputToppingCategory == 1) {
            toppings = regularToppings;
            selectedToppingCategory = "regular";
        } else if (inputToppingCategory == 2) {
            toppings = premiumToppings;
            selectedToppingCategory = "premium";
        }
        return selectedToppingCategory;
    }

    private String selectTopping() {
        String selectedToppingName = "";
        for (int i = 0; i < toppings.size(); i++) {
            System.out.println((i + 1) + ") " + toppings.get(i));
        }
        int inputTopping = scanner.nextInt();
        selectedToppingName = toppings.get(inputTopping - 1);
        return selectedToppingName;
    }

    private boolean addExtraTopping(String selectedToppingName) {
        boolean isExtra = false;
        System.out.print("Would you like to add extra " + selectedToppingName + " ? 1)yes  /2)no):");
        int extraChoice = scanner.nextInt();
        if (extraChoice == 1) {
            isExtra = true;
        }
        return isExtra;
    }

    private boolean addStuffed() {
        boolean isStuffed = false;
        System.out.print("Would you like the pizza with stuffed crust? 1)yes  /2)no)");
        int inputIfStuffedPizza = scanner.nextInt();

        if (inputIfStuffedPizza == 1) {
            isStuffed = true;
        }
        return isStuffed;
    }

    private boolean setPremiumCategory(String selectedToppingCategory) {
        return selectedToppingCategory.equals("premium");
    }
}
