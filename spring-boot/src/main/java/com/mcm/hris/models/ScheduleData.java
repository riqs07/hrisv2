package com.mcm.hris.models;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ScheduleData {
  private Date workdays;
  private Date payday;
  private int e_id;
}
