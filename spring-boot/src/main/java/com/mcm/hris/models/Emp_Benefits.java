package com.mcm.hris.models;

public class Emp_Benefits {



    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public int getBenefit_id() {
        return benefit_id;
    }

    public void setBenefit_id(int benefit_id) {
        this.benefit_id = benefit_id;
    }

    public int getNumDependants() {
        return numDependants;
    }

    public void setNumDependants(int numDependants) {
        this.numDependants = numDependants;
    }

    public double getIra() {
        return ira;
    }

    public void setIra(double ira) {
        this.ira = ira;
    }

    public double getBenefitSoloCost() {
        return benefitSoloCost;
    }

    public void setBenefitSoloCost(double benefitSoloCost) {
        this.benefitSoloCost = benefitSoloCost;
    }

    public double getBenefitCostPerDependant() {
        return benefitCostPerDependant;
    }

    public void setBenefitCostPerDependant(double benefitCostPerDependant) {
        this.benefitCostPerDependant = benefitCostPerDependant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    private int emp_id;
    private int benefit_id;
    private int numDependants;
    private double ira;
    private double benefitSoloCost;
    private double benefitCostPerDependant;
    private String description;






    // When Java inserts into DB
    public Emp_Benefits (int benefit_id,int emp_id) {
        this.benefit_id = benefit_id;
        this.emp_id = emp_id;

    }

    public Emp_Benefits() {

    }
}
