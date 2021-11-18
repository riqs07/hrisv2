package com.mcm.hris.modules;

import java.util.LinkedHashMap;

import com.mcm.hris.api.EmployeeDao;
import com.mcm.hris.api.LogInDao;
import com.mcm.hris.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;

public class StartMenu extends Module {

    private LinkedHashMap<String, Module> options;






    public StartMenu(EmployeeDao employeeDao, LogInDao logInDao) {
        options = new LinkedHashMap<>();
        options.put("Login", new Login(logInDao,employeeDao));
        options.put("Apply", new Apply());
        options.put("Quit", new Quit(this));

    }

    @Override
    public Module execute() {
        printWelcome();
        Utils.printOptions(options.keySet());
        int choice = Utils.promptForChoice(options.keySet());

        while (choice <= 0 || choice > options.size()) {
            Utils.printError("Invalid choice! Please enter a number between 1 and " + options.size() + "\n");
            choice = Utils.promptForChoice(options.keySet());
        }

        Module[] values = new Module[options.size()];
        options.values().toArray(values);
        return values[choice - 1];
    }

    private void printWelcome() {
        System.out.println("==================================================");
        System.out.println("            Welcome to McMillan HRIS!");
        System.out.println("==================================================");
    }

}
