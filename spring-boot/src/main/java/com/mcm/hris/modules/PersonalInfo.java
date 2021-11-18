package com.mcm.hris.modules;

import com.mcm.hris.models.Employee;
import com.mcm.hris.utils.Utils;

public class PersonalInfo extends Module {


    public PersonalInfo(Employee e) {

    }

    public Module execute() {
        Utils.printError("PersonalInfo module not yet implemented. Press enter to go back.\n");
        Utils.waitForInput();
        return null;
    }
}
