package com.mcm.hris.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeData {
  private int id;

  private String firstName;
  private String lastName;

  private String address;
  private String state;
  private int zipcode;

  private String email;
  private String phone_num;

  private String status;
  private String payType;

  public String getFullName() { return firstName + " " + lastName; }
}
