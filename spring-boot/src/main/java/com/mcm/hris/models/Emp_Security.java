package com.mcm.hris.models;

public class Emp_Security {


    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private int emp_id;
    private int question_id;
    private String answer;
    private String description;





    // When Java inserts into DB
    public Emp_Security (int emp_id,int question_id,String answer) {
        this.question_id = question_id;
        this.emp_id = emp_id;
        this.answer = answer;

    }

    public Emp_Security() {

    }
}
