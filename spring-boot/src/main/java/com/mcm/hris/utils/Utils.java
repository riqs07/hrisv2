package com.mcm.hris.utils;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

/**
 * Contains random functions that didn't fit in indiviual classes that are
 * probably useful.
 */
public class Utils {

    private enum Color {
        BLACK(30), RED(31), GREEN(32), YELLOW(33), BLUE(34), MAGENTA(35), CYAN(36), WHITE(37);

        private final int value;

        private Color(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

    private static Scanner input = new Scanner(System.in);

    public static void waitForInput() {
        input.nextLine();
    }

    /**
     * Prints all the options with a number next ot them starting from 1.
     * 
     * @param options list of options to be printed
     */
    public static void printOptions(Collection<String> options) {
        System.out.println("==================================================");

        int i = 1;
        for (String option : options) {
            System.out.printf("[%d]: %s\n", i, option);
            i++;
        }

        System.out.println("==================================================");
    }

    /**
     * Prints a nice looking menu and prompts the user for a choice.
     * 
     * @param choices container that holds all the choices for this module.
     * @return user chosen choice
     */
    public static int promptForChoice(Collection<String> choices) {
        System.out.printf("Enter an option [1-%d]: ", choices.size());
        String l = input.nextLine();

        return isNumber(l) ? Integer.parseInt(l) : -1;
    }

    /**
     * Prints a simple prompt in the form of: [text]:
     * 
     * @param text
     * @return user input in a String.
     */
    public static String prompt(String text) {
        System.out.print(coloredText(text + ": ", Color.GREEN));
        return input.nextLine();
    }

    /**
     * Prints a visible error for the user
     * 
     * @param msg the message to be printed
     */
    // TODO: Make the message text red
    public static void printError(String msg) {
        System.out.print(coloredText(msg, Color.RED));
    }

    /**
     * 
     * Tests whether or not s is a string that holds a number.
     * 
     * @param s the string to test
     * @return true if s holds a number and only an number, false otherwise.
     */
    public static boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }

    }

    /**
     * * Hashes str with the SHA-512 algorithm.
     * 
     * @param str the string to be hashed
     * @return resulting string of hexadecimal chars from SHA-512 hash
     */
    public static String sha512(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            byte[] strBytes = md.digest(str.getBytes());

            BigInteger no = new BigInteger(1, strBytes);

            String hashedStrInHex = no.toString(16);

            while (hashedStrInHex.length() < 32) {
                hashedStrInHex = "0" + hashedStrInHex;
            }

            return hashedStrInHex;

        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException(nsae);
        }
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern p = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return p.matcher(email).matches();
    }

    public static String coloredText(String text, Color foreground) {
        final String ESCAPE_CODE = "\u001b[";
        final String RESET_CODE = ESCAPE_CODE + "0m";
        return (ESCAPE_CODE + foreground.value() + "m" + text + RESET_CODE);
    }

    public static String coloredText(String text, Color foreground, Color background) {
        final String ESCAPE_CODE = "\u001b[";
        final String RESET_CODE = ESCAPE_CODE + "0m";
        return (ESCAPE_CODE + foreground.value() + ESCAPE_CODE + (background.value() + 10) + text + RESET_CODE);
    }
}
