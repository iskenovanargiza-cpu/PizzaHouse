package com.pluralsight.drink;

import java.util.List;
import java.util.Scanner;

public class AddDrink {
    private Scanner scanner;

    List<String> drinkFlavors = List.of(
            "Coke", "Sprite", "Fanta", "Water", "Iced Tea"
    );

    public AddDrink(Scanner scanner){
        this.scanner = scanner;
    }


    public String selectDrinkFlavor() {
        String selectedDrink = "";
        System.out.println("===Please select drink===");
        for (int i = 0; i < drinkFlavors.size(); i++) {
            System.out.println((i + 1) + ")" + drinkFlavors.get(i));
        }
        int inputDrink = scanner.nextInt();
        selectedDrink = drinkFlavors.get(inputDrink - 1);
        return selectedDrink;
    }

    public String selectDrinkSize() {
        String selectedDrinkSize = "";
        System.out.println("===Please select size===\n1)Small\n2)Medium\n3)Large");
        int inputDrinkSize = scanner.nextInt();
        if (inputDrinkSize == 1) {
            selectedDrinkSize = "Small";
        } else if (inputDrinkSize == 2) {
            selectedDrinkSize = "Medium";
        } else if (inputDrinkSize == 3) {
            selectedDrinkSize = "Large";
        }
        return selectedDrinkSize;
    }

}
