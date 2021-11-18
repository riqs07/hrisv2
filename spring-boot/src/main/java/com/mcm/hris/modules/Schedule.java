package com.mcm.hris.modules;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

import com.mcm.hris.api.EmployeeDao;

import com.mcm.hris.models.Emp_Job;
import com.mcm.hris.models.Employee;
import com.mcm.hris.models.ScheduleData;
import lombok.SneakyThrows;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import com.mcm.hris.utils.Utils;
import java.sql.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;


public class Schedule extends Module {


  static DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  private Employee employee;
  private Emp_Job empJob;
  private String empDept;
  boolean isManager;


  //  private ScheduleData schedule;
  private JdbcTemplate db;
  private static final Logger log = LoggerFactory.getLogger(Login.class);

  public LinkedHashMap<String, Integer> choices;
  Scanner scan = new Scanner(System.in);

  public Schedule(Emp_Job jobInfo, EmployeeDao employeeDao) {

    this.empJob = jobInfo;
    empDept = empJob.getDepartment();


    int flag = empJob.getIsManager();

    if (flag == 1) {
      isManager = true;
    }


    var ds = new SimpleDriverDataSource();
    try {
      ds.setDriver(new com.mysql.jdbc.Driver());
    } catch (SQLException sqle) {
      log.error("Could not create jdbc driver");
      sqle.printStackTrace();
    }
    ds.setUrl("jdbc:mysql://localhost:3306/mcmilliam");
    ds.setUsername("root");
    ds.setPassword("wolvesCDQ49!");


    choices = new LinkedHashMap<>();
    choices.put("Check Schedule", 1);
    choices.put("Time Off Request", 2);
    if (empDept.equals("HR")) {
      choices.put("Generate Schedule", 3);
      choices.put("View/approve Requests", 4);
      choices.put("Remove from schedule", 5);
    }
    if (isManager) {
      choices.put("View employee schedule", 6);
//    }
      choices.put("Go Back", 7);


    }


    db = new JdbcTemplate(ds);


  }

  @SneakyThrows
  @Override
  public Module execute() {
    System.out.println("_-_-_-_-Schedule-_-_-_-_\n");
    System.out.println("OPTIONS:\n\n");
    Utils.printOptions(choices.keySet());


//    boolean mainLoop = true;
    int schedule = Utils.promptForChoice(choices.keySet());
    while (schedule >= 1 && schedule <= choices.size()) {
      if (schedule == 1) {
        new Attendance();
      } else if (schedule == 2) {
        System.out.println("Taking you to the Time-off request menu");
        Thread.sleep(3000);
//        new ScheduleRequest();
      } else if (schedule == 3 && empDept.equals("HR")) {

        while (true) {
          int sc = 0;
          System.out.println("Would you like to add a date to the schedule?\n\n [1] yes \n [2] no");
          sc = scan.nextInt();
          ///second loop
          boolean flag = true;
          if (sc == 1) {

            System.out.println("What Date would you like to add to the Schedule? Format:(yyyy-MM-dd)");

            String rawDateString = scan.next();
            LocalDate Date = LocalDate.parse(rawDateString,date);

            int day = Date.getDayOfMonth();
            String sql = "insert into schedule(e_id) select id from employee where active_emp like 'Active'";

//            db.update(
//                    "insert into schedule(e_id) select id from employee where active_emp like 'Active'"
//            );



//            String sql = "update schedule set workdays = '?' where e_id is not null and workdays is null";
//            var rowMapper = BeanPropertyRowMapper.newInstance(ScheduleData.class);
//            db.update(sql, rowMapper, Date);
//


            // putting 1/15 breaks is
            if (day == 1 || day == 15) {
              db.update(
                      "update schedule set payday = 1 where workdays is null"

              );
//
//              String sql = "SELECT salary, manager, emp_job.dept, team.name,team.id FROM emp_job join job on job.id = emp_job.job_id join team on team.id = emp_job.team_id where e_id = ?;";
//              Emp_Job job = null;
//
//              try {
//                job = jdbc.queryForObject(sql,jobRowMapper,id);
//              } catch (DataAccessException ex){
//                System.out.println("Nothing Found");
//              }


            String sql2 = "update schedule set workdays = ? where e_id is not null and workdays is null";
              var rowMapper = BeanPropertyRowMapper.newInstance(ScheduleData.class);
               db.update(sql2, rowMapper, Date);

//              db.queryForObject(sql,rowMapper,Date);

//              db.update(
//                      "update schedule set workdays = '?' where e_id is not null and workdays is null", Date
//              );

            } else {
              db.update("update schedule set payday = 0 where workdays is null");
              db.update(
                      "update schedule set workdays = ? where e_id is not null and workdays is null", Date
              );
            }
//            continue;
          } else if (sc == 2) {
            System.out.println("Returning to Previous options");
//            flag = false;

            break;
          } else {
            System.out.println("INCORRECT Input please try again");
            continue;
          }

        }
break;
      }







      else if (schedule == 4) {
        System.out.println("Taking you to the Time-off requests menu");
        Thread.sleep(3000);
        new ScheduleRequest();
      } else if (schedule == 5 && empDept.equals("HR")) {
        System.out.println("Remove employees from the Schedule from ID and date");

        while (true) {
          System.out.println("Would you like to remove someone from the schedule?");
          System.out.println("[1] Yes \n [2] No");
          int sc = scan.nextInt();
          if (sc == 1) {
            System.out.println("Please enter the ID of the employee: ");
            int emp_id = scan.nextInt();
            System.out.println("Please enter the date to remove the employee from");


            String rawDateString = scan.next();
            LocalDate Date = LocalDate.parse(rawDateString,date);
            db.update(
                    "delete from schedule where e_id = ? and workdays = '?'", emp_id, Date
            );
          } else if (sc == 2) {
            System.out.println("test");
            break;
          } else {
            System.out.println("INCORRECT Choice please reenter an option");
            continue;
          }
        }

      } else if (schedule == 6 && isManager) {
        System.out.println("-----Manager View------");
        while (true) {
          System.out.println("Look at Team employee Schedules: \n [1] Yes \n [2] No");
          int j = scan.nextInt();
          if (j == 1) {
            String sql = "select schedule.workdays, schedule.e_id, emp_id.team_id from schedule join emp_job on emp_job.e_id = schedule.e_id where team_id = ?";
            var rowMapper = BeanPropertyRowMapper.newInstance(Emp_Job.class);
//            System.out.println(db.queryForObject(sql, rowMapper, empJob.getTeam_id()));

          }
          if (j == 2) {
            break;
          } else {
            System.out.println("Value entered is not in range please try again");
            continue;
          }
        }
      } else if (schedule == 7) {
        new GoBack();
      }
    }
    return null;
  }

}