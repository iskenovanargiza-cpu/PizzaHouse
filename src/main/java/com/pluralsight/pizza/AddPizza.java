package com.pluralsight.pizza;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddPizza {
    private Scanner scanner;
    List<String> regularToppings = List.of(
            "Onions", "Mushrooms", "Bell Peppers", "Olives",
            "Tomatoes", "Spinach", "Basil", "Pineapple", "Anchovies"
    );

    List<String> premiumMeetToppings = List.of(
            "Pepperoni", "Sausage", "Ham", "Bacon", "Chicken", "Meatball"

    );

    List<String> premiumCheeseToppings = List.of(
            "Mozzarella", "Parmesan", "Ricotta", "Goat Cheese", "Buffalo"
    );

    List<String> sauces = List.of(
            "Marinara", "Alfredo", "Pesto", "BBQ", "Buffalo", "Olive oil"
    );
    List<String> toppings = new ArrayList<>();

    public AddPizza(Scanner scanner) {
        this.scanner = scanner;
    }

    public String selectAndSetCrustType() {
        String selectedType = "";
        System.out.println("===Please select pizza crust===\n1)Thin\n2)Regular\n3)Thick\n4)Cauliflower");
        int inputCrustChoice = scanner.nextInt();
        scanner.nextLine();
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

    public String selectSize() {
        String selectedSize = "";
        System.out.println("===Please select pizza size===\n1)Personal 8\n2)Medium 12\n3)Large 16");
        int inputSize = scanner.nextInt();
        scanner.nextLine();
        if (inputSize == 1) {
            selectedSize = "Personal";
        } else if (inputSize == 2) {
            selectedSize = "Medium";
        } else if (inputSize == 3) {
            selectedSize = "Large";
        }
        return selectedSize;
    }

    public String selectToppingCategory() {
        String selectedToppingCategory = "";
        System.out.println("===Please select topping category===\n1)Regular Toppings\n2)Premium Toppings(extra cost)");
        int inputToppingCategory = scanner.nextInt();
        scanner.nextLine();

        if (inputToppingCategory == 1) {
            selectedToppingCategory = "regular";
            toppings = regularToppings;
        } else if (inputToppingCategory == 2) {
            System.out.println("===Please select premium topping===\n1)Meat \n 2)Cheese");
            int inputPremiumSelect = scanner.nextInt();
            if (inputPremiumSelect == 1) {
                toppings = premiumMeetToppings;
                selectedToppingCategory = "Premium meat category";
            } else if (inputPremiumSelect == 2) {
                toppings = premiumCheeseToppings;
                selectedToppingCategory = "Premium cheese category";
            }
        }
        return selectedToppingCategory;
    }


    public String selectTopping() {
        String selectedToppingName = "";
        for (int i = 0; i < toppings.size(); i++) {
            System.out.println((i + 1) + ") " + toppings.get(i));

        }
        int inputTopping = scanner.nextInt();
        scanner.nextLine();
        selectedToppingName = toppings.get(inputTopping - 1);
        return selectedToppingName;

    }

    public boolean addExtraTopping(String selectedToppingName) {
        boolean isExtra = false;
        System.out.print("Would you like to add extra " + selectedToppingName + " ? 1)yes / 2)no:");
        int extraChoice = scanner.nextInt();
        if (extraChoice == 1) {
            isExtra = true;
        }
        return isExtra;
    }

    public String selectSauce() {
        String selectedSauceName = "";
        System.out.println("===Please select sauce===");
        for (int i = 0; i < sauces.size(); i++) {
            System.out.println(i + 1 + ")" + sauces.get(i));
        }
        int inputSauce = scanner.nextInt();
        selectedSauceName = sauces.get(inputSauce - 1);
        return selectedSauceName;

    }

    public boolean addStuffed() {
        boolean isStuffed = false;
        System.out.print("Would you like the pizza with stuffed crust? 1)yes / 2)no:");
        int inputIfStuffedPizza = scanner.nextInt();

        if (inputIfStuffedPizza == 1) {
            isStuffed = true;
        }
        return isStuffed;
    }

    public boolean setPremiumCategory(String selectedToppingCategory) {
        return selectedToppingCategory.equals("premium");

    }
}