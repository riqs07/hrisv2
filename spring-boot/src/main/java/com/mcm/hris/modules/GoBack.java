package com.mcm.hris.modules;

// This, like QuitFlag is simply a flag to the main method to return to the menu before.
// Its execute() method is called, but it simply puts an alert saying its returning to the previous screen.
public class GoBack extends Module {
    public Module execute() {
        System.out.println("Returning to previous menu...\n");
        return null;
    }
}
