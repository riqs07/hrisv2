package com.company;

import java.time.LocalDate;

public class EmployeeTest {
    public static void main(String[] args) {
        Employee Oscar = new Employee("Oscar","the Grouch","1234 Seaseme St","NY","osc@gmail","123-312");
        Employee Tim = new Employee("Timmy ","Turner","90210 Rodeo","CA","osc@gmail","123-990");


        LocalDate testCheck1 = LocalDate.of(Integer.parseInt("2021"), Integer.parseInt("9"), Integer.parseInt("17"));
        LocalDate testCheck2 = LocalDate.of(Integer.parseInt("2021"), Integer.parseInt("9"), Integer.parseInt("10"));
        LocalDate testCheck3 = LocalDate.of(2021,5,1);
        LocalDate testCheck4 = LocalDate.of(2021,4,17);
        LocalDate testCheck5 = LocalDate.of(2021,3,3);


        Oscar.paychecks.addPaycheck(testCheck1,400);
        Oscar.paychecks.addPaycheck(testCheck2,2000);
        Oscar.paychecks.addPaycheck(testCheck3,600);

        Tim.paychecks.addPaycheck(testCheck1,200);
        Tim.paychecks.addPaycheck(testCheck4,900);
        Tim.paychecks.addPaycheck(testCheck5,600);


        Oscar.setPayRate(16);
        Oscar.setHoursWorked(61.5);
        Oscar.payEmployee();

        Tim.setPayRate(34);
        Tim.setHoursWorked(40);
        Tim.payEmployee();


        String checks = Oscar.paychecks.getChecks();
        String checks2 = Tim.paychecks.getChecks();


        System.out.println(checks);
        System.out.println(checks2);
    }
}
