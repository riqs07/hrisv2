package com.company;

import java.time.LocalDate;
import java.util.Scanner;

public class Employee {

    String firstName;
    String lastname;
    String state; // Has to be a valid State code
    String address;
    String zipcode;
    String city;
    String email;
    String phoneNumber;
    String location;
    String birthday;

    private String password;


    double hoursWorked;
    double payRate;

    EmergencyContact contact;
    Paychecks paychecks;


    boolean isWorking;
    boolean isActive;
    LocalDate startDate;


    public Employee(String firstName,String lastname,String address,String state,String email,String phoneNumber,String zipcode,String city,String birthday){
        this.firstName = firstName;
        this.lastname = lastname;
        this.address = address;
        this.state = state;
        this.email = email;
        this.city = city;
        this.zipcode = zipcode;
        this.phoneNumber = phoneNumber;
        this.startDate = LocalDate.now();
        this.paychecks = new Paychecks();
        this.isActive = true;

    }

    /////// Getters

    public String getName(){
        return this.firstName + " " + this.lastname;
    }

    public String getAddress(){
        return this.address + ", " + this.state;
    }

    public String getContactInfo(){
        return "@ " + this.email + " # " + this.phoneNumber;
    }


    public double getHoursWorked(){
        return this.hoursWorked;
    }
    public double getPayRate(){
        return this.payRate;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }

    public void setContact(EmergencyContact contact) {
        this.contact = contact;
    }

    public void showTimeClock(){
        // Get time clock data
        // parse it
        // report it
    }

    public void punchTimeClock(){

        this.isWorking = !isWorking;
        // Somehow update hours worked from here

    }

    public void payEmployee(){
        double pay = this.payRate * this.hoursWorked;
        System.out.println("Your Paycheck was " + pay);

        this.hoursWorked = 0;
        this.paychecks.addPaycheck(LocalDate.now(),pay) ;


    }

    public  void report(){
        System.out.println("Name: " + this.firstName + " " + this.lastname + "\nBirthday: " + this.birthday + " " + "\nAddress: " + this.address + ", " + this.zipcode + ", " + this.city + ", " + this.state + "\n #" + this.phoneNumber + "\n @" + this.email);

    }

    public static void insertNewEmployee() {

        System.out.println("This will insert a new employee. Please enter your info");
        Scanner scan = new Scanner(System.in);

        System.out.println("First lets get some basic info......");
        //string builder
        System.out.println("Enter Employee First Name");
        String fname = scan.nextLine();

        System.out.println("Enter Employee Last Name");
        String lname = scan.nextLine();

        System.out.println("Enter birthdate using DD/MM/YYYY format");
        // save bday as well as get age from bday
        String birthday = scan.nextLine();


        System.out.println("Now, Lets get the contact info");
        System.out.println("Enter Phone Number ###-###-####");

        String number = scan.nextLine();
        System.out.println("Enter Email address");
        String email = scan.nextLine();


        System.out.println("Now, Lets get the location Info");
        System.out.println("Enter Mailing address");
        String address = scan.nextLine();

        System.out.println("Enter Zipcode");
        String zip = scan.nextLine();

        System.out.println("Enter City");
        String city = scan.nextLine();

        System.out.println("Enter State code. eg. NC, TX, NY");

        String state = scan.nextLine();



        boolean confirmed = false;


        while (!confirmed) {

            System.out.println("Please Confirm all info... Enter Y/N");
            System.out.println("Name: " + fname + " " + lname + "\nBirthday: " + birthday + " " + "\nAddress: " + address + ", " + zip + ", " + city + ", " + state + "\n# " + number + "\n@ " +email);
            String response = scan.nextLine();

            if (response.equals("Y")) {
                // Check DB for duplicates
                System.out.println("Success!" + fname + " " + lname + " has been registered as a new employee");
                Employee Sally =  new Employee(fname,lname,address,state,email,number,zip,city,birthday);

                Sally.report();

                // insert into database
                confirmed = true;
            } else if (response.equals("N")) {
                System.out.println("What would you like to change? Enter Selection");
                System.out.println("1. Name \n2.Birthday\n 3. Address ");
                int choice = scan.nextInt();

                switch (choice){
                    case 1:
                        System.out.println("Enter Correct First Name");
                        fname = scan.nextLine();
                        System.out.println("Enter Correct Last Name");
                        lname = scan.nextLine();
                        break;
                    case 2:
                        System.out.println("Enter Correct birthdate using DD/MM/YYYY format");
                         birthday = scan.nextLine();
                    case 3:
                        System.out.println("Enter Correct Address");
                        System.out.println("Enter Zipcode");
                         zip = scan.nextLine();

                        System.out.println("Enter City");
                         city = scan.nextLine();

                        System.out.println("Enter State code. eg. NC, TX, NY");
                         state = scan.nextLine();
                        break;

                }

                continue;



            }
            if (response.equals("quit")) {
                // ends loop
                break;

            }

            if (response.equals("back")) {
                /// moves control to top
                continue;
//            }
//        }


            }
        }
    }

    public static void main(String[] args) {
        Employee.insertNewEmployee();

    }
}
