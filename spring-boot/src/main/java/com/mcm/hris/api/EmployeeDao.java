package com.mcm.hris.api;

import com.mcm.hris.models.*;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class EmployeeDao {
    private JdbcTemplate jdbc;

    public EmployeeDao(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }




    // Maps Each Row To a Java Object
    RowMapper<Employee> employeeRowMapper = (rs, rowNum) -> {
        Employee employee = new Employee();

        employee.setId(rs.getInt("id"));
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setAddress(rs.getString("address"));
        employee.setZipCode(rs.getString("zipCode"));
        employee.setPhoneNum(rs.getString("phone_num"));
        employee.setActive_emp(rs.getString("active_emp"));
        employee.setPay_type(rs.getString("pay_type"));
        employee.setStatus(rs.getString("active_emp"));
        employee.setState(rs.getString("state"));



        return employee;
    };


    RowMapper<Emp_Training> trainingRowMapper = (rs, rowNum) -> {
        Emp_Training trainings = new Emp_Training();

        trainings.setName(rs.getString("name"));

        trainings.setStatus(rs.getString("status"));
        trainings.setTraining_desc(rs.getString("description"));



        return trainings;
    };

    RowMapper<Emp_Benefits> benefitsRowMapper = (rs, rowNum) -> {
        Emp_Benefits benefits = new Emp_Benefits();


        benefits.setNumDependants(rs.getInt("numof_dependants"));
        benefits.setIra(rs.getDouble("401k"));
        benefits.setBenefitSoloCost(rs.getDouble("cost"));
        benefits.setBenefitCostPerDependant(rs.getDouble("costPerDependant"));
        benefits.setDescription(rs.getString("description"));



        return benefits;
    };

    RowMapper<Emp_Security> securityRowMapper = (rs, rowNum) -> {
        Emp_Security questions = new Emp_Security();

        questions.setAnswer(rs.getString("answer"));
        questions.setDescription((rs.getString("Description")));

return  questions;
    };

    RowMapper<Performance> performanceRowMapper= (rs, rowNum) -> {
        Performance report = new Performance();

        report.setDate(rs.getString("Date"));
        report.setDate(rs.getString("Comments"));
        report.setScore(rs.getInt("Rating"));

        return  report;
    };




    RowMapper<Emp_Job> jobRowMapper =  (rs, rowNum) -> {
        Emp_Job job = new Emp_Job();

        job.setSalary(rs.getDouble("salary"));
        job.setIsManager((rs.getInt("manager")));
        job.setTeamName((rs.getString("name")));
        job.setTeam_id((rs.getInt("id")));
        job.setDepartment((rs.getString("dept")));
//        job.setManagerFirstName((rs.getString("first_name")));
//        job.setManagerLastName((rs.getString("last_name")));

        return  job;
    };




    public List<Employee> getEmployees() {
        String sql = "SELECT * FROM EMPLOYEE";

        return jdbc.query(sql, employeeRowMapper);

    }

    public Employee getEmployeeByID(int id){

        String sql = "Select * from EMPLOYEE where id = ?";

        Employee employee = null;

        try {
            employee = jdbc.queryForObject(sql, employeeRowMapper,id);
        } catch (DataAccessException ex){
            System.out.println("Nothing Found");
        }

        return employee;
    };


    public Employee getEmployeeByEmail(String email){

        String sql = "SELECT * FROM Employee WHERE email=?";
        return jdbc.queryForObject(sql, employeeRowMapper, email);
    };


    RowMapper<LogInData> logInDataRowMapper = (rs, rowNum) -> {
        LogInData credentials = new LogInData();


        credentials.setEmployee_id(rs.getInt("id"));
        credentials.setUsername(rs.getString("username"));
        credentials.setPassword(rs.getString("password"));


        return credentials;
    };


    public String getPassFromDb(String email) {
        String password;
        String sql = "SELECT id,username,password FROM logins join employee on employee.email = username where username = ?;";
        try {
            LogInData credentials = jdbc.queryForObject(sql,logInDataRowMapper,email);
            password = credentials.getPassword();

        } catch (EmptyResultDataAccessException erdae) {
            return null;
        }

        return password;
    }










    /////////////// SELF SERVICE /////////////

    public List<Emp_Training> getTrainingsByID(int id){


        String sql = "SELECT training_id,name,status,description FROM emp_training join training on emp_training.training_id = training.id where e_id = ?";
        List<Emp_Training> training = null;

        try {
            training = jdbc.query(sql,trainingRowMapper,id);
        } catch (DataAccessException ex){
            System.out.println("Nothing Found");
        }



        return training;


    };




    public List<Emp_Benefits> getBenefitsByID(int id){
        String sql = "SELECT numof_dependants,`401k`,cost,costPerDependant,description FROM emp_benefits join benefits on b_id = benefits.id where e_id = ?;";
        List<Emp_Benefits> benefits = null;

        try {
            benefits = jdbc.query(sql,benefitsRowMapper,id);
        } catch (DataAccessException ex){
            System.out.println("Nothing Found");
        }



        return benefits;
    }

    public void updateBenefits(Emp_Benefits benefits){}


    public List<Performance> getPerfomanceReportsByID(int id){
        String sql = "SELECT m_id,Date,Comments,Rating FROM performance where e_id = ?;";

        List<Performance> reports = null;

        try {
            reports = jdbc.query(sql,performanceRowMapper,id);
        } catch (DataAccessException ex){
            System.out.println("Nothing Found");
        }



        return reports;
    }


    public Emp_Security getSecurityQuestion(int id){
        String sql = "select Description, answer from emp_security join sec_question on question_id = sec_question.id where e_id =?";
        Emp_Security secQuestion = null;

        try {
            secQuestion = jdbc.queryForObject(sql,securityRowMapper,id);
        } catch (DataAccessException ex){
            System.out.println("Nothing Found");
        }



        return secQuestion;
    }
    public void updateSecQuestion(Emp_Security question){}


    public Emp_Job getJobInfo(int id){
        String sql = "SELECT salary, manager, emp_job.dept, team.name,team.id FROM emp_job join job on job.id = emp_job.job_id join team on team.id = emp_job.team_id where e_id = ?;";
        Emp_Job job = null;

        try {
            job = jdbc.queryForObject(sql,jobRowMapper,id);
        } catch (DataAccessException ex){
            System.out.println("Nothing Found");
        }



        return job;
    }


}
