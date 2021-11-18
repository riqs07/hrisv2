package com.mcm.hris.modules;

import com.mcm.hris.api.EmployeeDao;
import com.mcm.hris.models.Emp_Job;
import com.mcm.hris.models.Employee;
import com.mcm.hris.models.EmployeeData;
import com.mcm.hris.utils.Utils;
import java.util.LinkedHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeMenu extends Module {

  private static final Logger log = LoggerFactory.getLogger(EmployeeMenu.class);

  public Employee info;
  public EmployeeDao employeeDao;
  private Emp_Job empJob;

  public int empID;




  // Options avaiable for MainMenu
  // Use LinkedHashMap because it maintains order of insertion.
  private LinkedHashMap<String, Module> options;

  public EmployeeMenu(Employee e, EmployeeDao employeeDao) {

    this.info = e;
    this.empID = e.getId();
    this.employeeDao = employeeDao;

    empJob = employeeDao.getJobInfo(empID);





    System.out.println("\n Hello " + info.getFullName() + " !");

    options = new LinkedHashMap<>();
    options.put("Clock In/Out", new ClockInOrOut(empID));
    options.put("Payroll", new Payroll());
    options.put("Personal Info", new PersonalInfo(e));
    options.put("Schedule", new Schedule(empJob,employeeDao));
    options.put("Back", new GoBack());
  }

  @Override
  public Module execute() {
    printWelcome();
    Utils.printOptions(options.keySet());
    int choice = Utils.promptForChoice(options.keySet());

    // Handle invalid choice
    while (choice <= 0 || choice > options.size()) {
      Utils.printError("Invalid choice! Please enter a number between 1 and " +
                       options.size() + "\n");
      choice = Utils.promptForChoice(options.keySet());
    }

    Module[] values = new Module[options.size()];
    options.values().toArray(values);
    return values[choice - 1];
  }

  private void printWelcome() {
    System.out.println("==================================================");
//    System.out.printf("Welcome %s!\n", this.employee.getFullName());
    System.out.println("==================================================");
  }
}
