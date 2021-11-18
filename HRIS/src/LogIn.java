import java.util.Scanner;

public class LogIn {
    public static boolean logIn() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to McMillan HRIS.");
        boolean isLoggedIn = false;
        while (isLoggedIn == false) {
            System.out.println("Type in your username.");
            String usernameGuess = scan.nextLine();
            if (usernameGuess.equals("Caius_Cooke")) {
                System.out.println("Correct username. Now type in the password!");
                while (isLoggedIn == false) {
                    String passwordGuess = scan.nextLine();
                    if (passwordGuess.equals("password!")) {
                        isLoggedIn = true;
                    } else {
                        System.out.println("Password incorrect, try again");
                    }
                }
            } else {
                System.out.println("incorrect username, try again");
            }
        }
        return isLoggedIn = true;
    }

    public static void usernameAndPassword() {
        String username = "Caius_Cooke";
        String password = "password!";
    }
}
