package com.mcm.hris.modules;

import java.util.Scanner;

import com.mcm.hris.utils.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Confirms that the user really wants to quit
// If the user denies, returns the last module that was being used
// otherwise it returns the quit flag and exits the program.
public class Quit extends Module
{

    public static Logger log = LoggerFactory.getLogger(Quit.class);

    private Module previousModule;

    public Quit(Module previousModule)
    {
        this.previousModule = previousModule;
    }

    public Module execute()
    {
        System.out.print("Do you really want to quit? [Y/N]: ");
        Scanner input = new Scanner(System.in);
        char choice = input.next().toLowerCase().charAt(0);

        boolean done = false;
        while (!done) {
            switch (choice)
            {
                case 'y':
                    return new QuitFlag();
                case 'n':
                    return previousModule;
                default:
                    done = false;
                    Utils.printError("Invalid input! Do you really want to quit? [Y/N]: ");
                    choice = input.next().toLowerCase().charAt(0);
            }
        }

        if (choice == 'n') {
            return previousModule;
        }
        return new QuitFlag();
    }
}
