package com.mcm.hris.modules;

// This class is only here to flag to the main method to quit.
// its execute() can have code in it , but it will never be executed.
public class QuitFlag extends Module {
    public Module execute() {
        return null;
    }

}
