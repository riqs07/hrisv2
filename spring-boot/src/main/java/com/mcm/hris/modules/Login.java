package com.mcm.hris.modules;

import com.mcm.hris.api.EmployeeDao;
import com.mcm.hris.api.LogInDao;

import com.mcm.hris.models.Employee;
import com.mcm.hris.utils.Utils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Login extends Module {



  private static final Logger log = LoggerFactory.getLogger(Login.class);


 private final LogInDao dao;
 private final EmployeeDao empDao;

 public Login(LogInDao dao, EmployeeDao empDao){
     this.dao = dao;
     this.empDao = empDao;
 }

 public boolean load(String email){
     System.out.println("work2");
   return dao.userExists(email);
 }

  /**
   * prompt the user to login. Checks for valid emails allows for three tries
   * for password entry.
   */


  @Override
  public Module execute() {
    showWelcome();


    String email = Utils.prompt("E-mail");






    while (!dao.userExists(email)) {
      Utils.printError("User not found! Please try again.\n");
      email = Utils.prompt("E-mail");
    }

    while (!Utils.isValidEmail(email)) {
      Utils.printError("Please enter an @mcmillan.com email.\n");
      email = Utils.prompt("E-mail");
    }

//    String correctPassword = getPassFromDb(email);
    String correctPassword = "pass";
    if (correctPassword == null) {
      // This is the first time this user is logging in,

      // have them create a password
      String newPassword = Utils.prompt("Please create a new password");
      String confirmation = Utils.prompt("Please confirm your password");
      while (!confirmation.equals(newPassword)) {
        Utils.printError("Your passwords don't match please try again!\n");
        confirmation = Utils.prompt("Please confirm your password");
      }
//      newPassword = Utils.sha512(newPassword);
      newPassword = newPassword;
      dao.setPassword(email, newPassword);

      System.out.println("Thank you! Please choose a security question:");

      // and select a security question
      var questions = dao.getSecurityQuestions();
      Utils.printOptions(questions);
      int questionId = Utils.promptForChoice(questions);
      while (questionId <= 0 || questionId > questions.size()) {
        Utils.printError(
            "Invalid choice! Please enter a number between 1 and " +
            questions.size() + "\n");
        questionId = Utils.promptForChoice(questions);
      }

      var selectedQuestion = questions.get(questionId - 1);
      String answer = Utils.prompt(selectedQuestion);
      confirmation = Utils.prompt("Please confirm your answer");
      while (!confirmation.equals(answer)) {
        Utils.printError("Your answer doesn't match! Please try again!\n");
        confirmation = Utils.prompt("Please confirm your answer");
      }
      dao.setSecurityQuestion(email, questionId, answer);

      System.out.println("Thank you! Welcome to McMillan!");

      // Once they set these just log them in.
      Employee employee = empDao.getEmployeeByEmail(email);
      log.info("Employee data: {}", employee);
      return new EmployeeMenu(employee,empDao);
    }









    // Give them three tries to login
    int tries = 3;
    while (tries != 0) {
//      String userEnteredPassword = Utils.sha512(Utils.prompt("Password"));

      String userEnteredPassword = Utils.prompt("Password");

      if (userEnteredPassword.equals(correctPassword)) {

        Employee employee = empDao.getEmployeeByEmail(email);
        log.info("Employee data: {}", employee);
        return new EmployeeMenu(employee,empDao);
      }

      Utils.printError(
          String.format("Invalid password! Tries left: (%d)\n", tries));
      tries--;
    }

    Utils.printError("\nPassword check failed! Returning to main menu..\n");
    return null;
  }
//
//  private void setPassword(String email, String password) {
//    String sql = "UPDATE logins SET password=? WHERE username=?";
//    jdbc.update(sql, password, email);
//  }
//
//  private String getPassFromDb(String email) {
//    String sql = "SELECT password FROM logins WHERE username=?";
//    try {
//      String hashedPassword = jdbc.queryForObject(sql, String.class, email);
//      return hashedPassword;
//    } catch (EmptyResultDataAccessException erdae) {
//      return null;
//    }
//  }
//
//  private boolean userExists(String email) {
//    String sql = "SELECT COUNT(*) FROM logins WHERE username=?";
//    return jdbc.queryForObject(sql, Integer.class, email) > 0;
//  }

//  private EmployeeData getEmployeeFromDb(String email) {
//    String sql = "SELECT * FROM employee WHERE email=?";
//    var rowMapper = BeanPropertyRowMapper.newInstance(EmployeeData.class);
//    return jdbc.queryForObject(sql, rowMapper, email);
//  }

  private void showWelcome() {
    System.out.println("==================================================");
    System.out.println("                     Please login");
    System.out.println("==================================================");
  }
//
//  private List<String> getSecurityQuestions() {
//    String sql = "SELECT description FROM sec_question";
//    return jdbc.queryForList(sql, String.class);
//  }
//
//  private void setSecurityQuestion(String employeeEmail, int questionId,
//                                   String answer) {
//    String sql = "SELECT id FROM employee WHERE email=?";
//
//    int employeeId = jdbc.queryForObject(sql, Integer.class, employeeEmail);
//
//    sql =
//        "INSERT INTO emp_security (e_id, question_id, answer) VALUES (?, ?, ?)";
//    Map<String, Object> params = new HashMap<>();
//    params.put("e_id", employeeId);
//    params.put("question_id", questionId);
//    params.put("answer", answer);
//
//    new SimpleJdbcInsert(jdbc).withTableName("emp_security").execute(params);
//  }
}
