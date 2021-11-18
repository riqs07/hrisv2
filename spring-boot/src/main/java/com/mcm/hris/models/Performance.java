package com.mcm.hris.models;

public class Performance {
    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private int emp_id;
    private int manager_id;
    private String date;
    private String comments;
    private int score;






    // When Java inserts into DB
    public Performance (int manager_id,int emp_id,String date,String comments,int score) {
        this.manager_id = manager_id;
        this.emp_id = emp_id;
        this.comments = comments;
        this.score = score;

    }

    public Performance() {

    }
}
