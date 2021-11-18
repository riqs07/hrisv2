package com.mcm.hris.api;

import com.mcm.hris.models.LogInData;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LogInDao {

    ////Connect to DB
    private JdbcTemplate jdbc;

    public LogInDao(JdbcTemplate jdbc){
        this.jdbc = jdbc;

    }

    RowMapper<LogInData> rowMapper = (rs, rowNum) -> {
        LogInData credentials = new LogInData();


        credentials.setEmployee_id(rs.getInt("id"));
        credentials.setUsername(rs.getString("username"));
        credentials.setPassword(rs.getString("password"));


        return credentials;
    };



    public void setPassword(String email, String password) {
        String sql = "UPDATE logins SET password=? WHERE username=?";
        jdbc.update(sql, password, email);
    }

    public String getPassFromDb(String email) {
        String sql = "SELECT password FROM logins WHERE username=?";
        try {
            String hashedPassword = jdbc.queryForObject(sql, String.class, email);
            return hashedPassword;
        } catch (EmptyResultDataAccessException erdae) {
            return null;
        }
    }

    public boolean userExists(String email) {
        String sql = "SELECT COUNT(*) FROM logins WHERE username=?";
        return jdbc.queryForObject(sql, Integer.class, email) > 0;
    }



    public List<String> getSecurityQuestions() {
        String sql = "SELECT description FROM sec_question";
        return jdbc.queryForList(sql, String.class);
    }

    public void setSecurityQuestion(String employeeEmail, int questionId,
                                    String answer) {
        String sql = "SELECT id FROM employee WHERE email=?";

        int employeeId = jdbc.queryForObject(sql, Integer.class, employeeEmail);

        sql =
                "INSERT INTO emp_security (e_id, question_id, answer) VALUES (?, ?, ?)";
        Map<String, Object> params = new HashMap<>();
        params.put("e_id", employeeId);
        params.put("question_id", questionId);
        params.put("answer", answer);

        new SimpleJdbcInsert(jdbc).withTableName("emp_security").execute(params);
    }





}
