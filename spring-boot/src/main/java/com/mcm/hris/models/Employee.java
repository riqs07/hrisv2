package com.mcm.hris.models;

public class Employee {

    /// Define Employee Variables

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getActive_emp() {
        return active_emp;
    }

    public void setActive_emp(String active_emp) {
        this.active_emp = active_emp;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String zipCode;
    private String phoneNum;
    private String active_emp;
    private String pay_type;
    private String status;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String state;




    // No args Construcor

    public Employee(){

    }

// Completed Construcotr
    public Employee(String firstName, String lastName,String email,String state, String address, String zipCode, String phoneNum, String active_emp, String pay_type, String status ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.zipCode = zipCode;
        this.phoneNum = phoneNum;
        this.active_emp = active_emp;
        this.pay_type = pay_type;
        this.status = status;
        this.state = state;

    }

    // Semi Complete constructor



}
