package com.mcm.hris;

import com.mcm.hris.api.EmployeeDao;
import com.mcm.hris.api.LogInDao;
import com.mcm.hris.models.Employee;
import com.mcm.hris.models.Performance;

import com.mcm.hris.modules.GoBack;
import com.mcm.hris.modules.Module;
import com.mcm.hris.modules.QuitFlag;
import com.mcm.hris.modules.StartMenu;
import com.mcm.hris.utils.Utils;

import java.util.List;
import java.util.Stack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class HrisApplication implements CommandLineRunner {

  @Autowired
  public EmployeeDao employeeDao;
  private LogInDao logInDao;

  private static final Logger log =
      LoggerFactory.getLogger(HrisApplication.class);





  public HrisApplication(EmployeeDao employeeDao,LogInDao logInDao){
    this.employeeDao = employeeDao;
    this.logInDao = logInDao;

  }


  public static void main(String[] args) {
    SpringApplication.run(HrisApplication.class, args);
  }

  // Welcome user and prompt them to login.
  // Program starts here.
  @Override
  public void run(String... strings) throws Exception {

//    		Employee employee = employeeDao.getEmployeeByEmail("tomwsaw@mcmillian.com");
//		System.out.println(employee.getFirstName());



    var moduleStack = new Stack<Module>();
    moduleStack.push(new StartMenu(employeeDao,logInDao));

    while (!moduleStack.isEmpty()) {
      var mod = moduleStack.peek();
      // mod.execute() returns the next module to be displayed.
      // if that module is null or an instance of GoBack, we go back to the
      // previous module.
      var nextModule = mod.execute();

      if (nextModule != null) {
        if (nextModule instanceof QuitFlag) {
          break;
        }
        if (nextModule instanceof GoBack) {
          nextModule.execute();
          moduleStack.pop();
          continue;
        }
        moduleStack.push(nextModule);
      } else {
        moduleStack.pop();
      }
    }

    // Wait for user to press button to exit.
    System.out.println("Press enter to exit...");
    Utils.waitForInput();
  }
}
