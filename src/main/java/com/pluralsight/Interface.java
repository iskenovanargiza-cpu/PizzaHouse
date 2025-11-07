package com.pluralsight;
import java.util.Scanner;

public class Interface {
    Scanner scanner = new Scanner(System.in);

    public Interface(){
        System.out.println("!Program starts here!");
}
public void display(){

        do {
            homeScreen();

            System.out.print("Please select your option here:");
            int userInput = scanner.nextInt();
            scanner.nextLine();

            switch(userInput){
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

    public void orderScreen(){
        System.out.println("1) Add Pizza\n2) Add Drink\n3) Add Garlic Knots\n4) Checkout\n0) Cancel Order");
        int userInput = scanner.nextInt();

        switch(userInput){
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

    public void processAddPizza(){
        typePizzaDisplay();

        System.out.println("Write the type of crust you would like:\n");
        String userInput = scanner.nextLine();

        switch(userInput){
            case 1 -> processAddPizza();

        }



    }

    public void typePizzaDisplay(){
        System.out.println("===Pizza Crust===\n -thin\n -regular\n -thick\n -cauliflower");
    }










}
