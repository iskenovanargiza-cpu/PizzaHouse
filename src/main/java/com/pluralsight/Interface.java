package com.pluralsight;
import java.util.Scanner;

public class Interface {
    Scanner scanner = new Scanner(System.in);
    Order order = new Order();

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
        crustTypeScreen();
        System.out.println("Write the type of crust you would like:\n");
        String crustType = scanner.nextLine();
        sizeScreen();
        System.out.println("Choose the size");





    }

    public void crustTypeScreen(){
        System.out.println("===Pizza Crust===\n -thin\n -regular\n -thick\n -cauliflower");
    }

    public void sizeScreen() {
        System.out.println("===Pizza Type===\n 1 - Personal 8\n2 - Medium 12\n3 - Large 16");
    }













}
