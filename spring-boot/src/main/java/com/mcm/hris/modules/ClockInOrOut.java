package com.mcm.hris.modules;

import com.mcm.hris.models.Employee;
import com.mcm.hris.utils.Utils;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class ClockInOrOut extends Module {
  private int empID;

  Scanner scan =
      new Scanner(System.in); // jordans code to establish manual database
                              // connection probably not nessacary

  private JdbcTemplate jdbc;
  private static final Logger log = LoggerFactory.getLogger(Login.class);

  DateTimeFormatter time = DateTimeFormatter.ofPattern(
      "HH:mm"); // easy way to call the current time without dipping into sql
  LocalDateTime currTime = LocalDateTime.now();
  DateTimeFormatter date = DateTimeFormatter.ofPattern(
      "MM/dd/yyyy"); // easy way to call the current date without dipping into
                     // sql
  LocalDateTime currDate = LocalDateTime.now();

  public LinkedHashMap<String, String>
      choices; // uses the util classes to help set up a cleaner interface/code

  public ClockInOrOut(int id) {

    var ds = new SimpleDriverDataSource();
    try {
      ds.setDriver(new com.mysql.jdbc.Driver());
    } catch (SQLException sqle) {
      log.error("Could not create jdbc driver");
      sqle.printStackTrace();
    }

    // Use the same creds as in application.properties
    ds.setUrl("jdbc:mysql://localhost:3306/mcmilliam");
    ds.setUsername("root");
    ds.setPassword("wolvesCDQ49!");




    jdbc = new JdbcTemplate(ds);


    this.empID = id;

    choices = new LinkedHashMap<>();
    choices.put("Clock In", "1");
    choices.put("Lunch clock out", "2");
    choices.put("Lunch clock in", "3");
    choices.put("Clock out", "4");
    choices.put("Go Back to previous menu", "5");

  }


  @Override
  public Module execute() {
    int timeClock = 0;
    System.out.println(
        "==============================================================================\n\n\n");
    System.out.println(
        "          _--__-_-_-_-_-_-_-_Time Clock_-_-_-_-_-_-_-__--_                          ");
    System.out.println("Today's date is: " + date.format(currDate));
    System.out.println("-----------Options-------------");

    Utils.printOptions(choices.keySet());
    timeClock = Utils.promptForChoice(choices.keySet());

    while (timeClock >= 1 && timeClock <= choices.size()) {

      if (timeClock == 1) { // This is a check to see what option they chose
        System.out.println(
            "Are you sure you want to clock in at: " + time.format(currTime) +
            " \n1) Yes \n2) No"); // Double checks to ensure they want to clock
                                  // in
        int clockCheck = scan.nextInt();
        if (clockCheck == 1) {
          jdbc.update("INSERT INTO mcmilliam.time_in (e_id) VALUE (?)", empID);

          System.out.println("You successfully clocked in at " +
                             time.format(currTime));
          break;
        } else
          continue;
      } else if (timeClock == 2) {
        System.out.println("Are you sure you want to take your lunch at: " +
                           time.format(currTime) + " \n1) Yes \n2) No");

        int clockCheck = scan.nextInt();

        if (clockCheck == 1) {
          jdbc.update("INSERT INTO mcmilliam.time_out (e_id) VALUE (?)", empID);
          System.out.println("You successfully clocked out for lunch at: " +
                             time.format(currTime));
          break;
        } else
          continue;
      } else if (timeClock == 3) {
        System.out.println(
            "Are you sure you want to come back from lunch at: " +
            time.format(currTime) +
            " \n1) Yes \n2) No"); // Double checks to ensure they want to take a
                                  // lunch

        int clockCheck = scan.nextInt();

        if (clockCheck == 1) {
          jdbc.update("INSERT INTO mcmilliam.time_in (e_id) VALUE (?)", empID);
          System.out.println("You successfully clocked back in at: " +
                             time.format(currTime));
          break;
        } else
          continue;
      } else if (timeClock == 4) {
        System.out.println(
            "Are you sure you want to clock out at: " + time.format(currTime) +
            " \n1) Yes \n2) No"); // Double checks to ensure they want to take a
                                  // lunch
        int clockCheck = scan.nextInt();
        if (clockCheck == 1) {
          jdbc.update("INSERT INTO mcmilliam.time_out (e_id) VALUE (?)", empID);
          System.out.println("You successfully clocked out at: " +
                             time.format(currTime));
          break;
        } else
          continue;

      } else if (timeClock == 5) {
        return new GoBack();
      }
      while (timeClock <= 0 || timeClock > choices.size()) {
        Utils.printError(
            "Invalid choice! Please enter a number between 1 and " +
            choices.size() + "\n");
        timeClock = Utils.promptForChoice(choices.keySet());
      }
    }

    return null;
  }
}
