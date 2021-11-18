import java.util.Scanner;

public class PrintMenu {
    public static void printDirections() {
        System.out.println("Type a number to select the corresponding action:");
    }
    public static void mainMenuOptions(){
        System.out.println("Welcome to McMillan HR Information Systems!");
        printDirections();
        System.out.println("1. Go to time-clock");
        System.out.println("2. Go to payroll information");
        System.out.println("More options to come later!");
    }
    public static void clockInMenu(){
        printDirections();
        System.out.println("1. Clock in");
        System.out.println("2. Return to main menu");
        Scanner scan = new Scanner(System.in);
        String menuSelection = scan.next();
        if (menuSelection.equals("1")) {
            boolean clockedIn = true;
            System.out.println("Get to work!");
        } else {
            return; }
    }
    public static void clockedInMenu(){
        printDirections();
        System.out.println("1. Clock out");
        System.out.println("2. Go to lunch");
        System.out.println("3. Go to break");

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean isLoggedIn = LogIn.logIn();
        while(isLoggedIn == true) {
            mainMenuOptions();
            String menuSelection = scan.next();
            if (menuSelection.equals(1)) ;
            {
                clockInMenu();
            }
        }
    }
}
