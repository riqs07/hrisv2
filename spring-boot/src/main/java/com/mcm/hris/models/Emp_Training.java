package com.mcm.hris.models;

public class Emp_Training {

    private int training_id;
    private int emp_id;
    private String status;

    private String training_desc;



    private String name;




    // When Java inserts into DB
    public Emp_Training(int training_id,int emp_id) {
        this.training_id = training_id;
        this.emp_id = emp_id;

    }

    public Emp_Training() {

    }


    public int getTraining_id() {
        return training_id;
    }

    public void setTraining_id(int training_id) {
        this.training_id = training_id;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTraining_desc() {
        return training_desc;
    }

    public void setTraining_desc(String training_desc) {
        this.training_desc = training_desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
