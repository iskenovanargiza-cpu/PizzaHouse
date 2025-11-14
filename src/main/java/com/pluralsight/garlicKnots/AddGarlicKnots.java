package com.pluralsight.garlicKnots;

import java.util.Scanner;

public class AddGarlicKnots {
    private Scanner scanner;

    public AddGarlicKnots(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getCountOfAddedGarlicKnots() {
        System.out.println("Add garlic knots to your order ? 1)yes / 2)no");
        int inputAddKnots = scanner.nextInt();
        if (inputAddKnots == 1) {
            System.out.println("How many would you like to add? (Give a count, for Example: 5)");
            return scanner.nextInt();
        }
        return 0;
    }
}
