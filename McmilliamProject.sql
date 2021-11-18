CREATE DATABASE `mcmilliam` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
CREATE TABLE `attendance` (
  `date` datetime DEFAULT CURRENT_TIMESTAMP,
  `clock_in` timestamp NULL DEFAULT NULL,
  `clock_out` timestamp NULL DEFAULT NULL,
  `emp_id` decimal(9,0) NOT NULL,
  KEY `emp_id` (`emp_id`),
  CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `benefits` (
  `benefits_package` int NOT NULL AUTO_INCREMENT,
  `cost` decimal(6,2) NOT NULL,
  `costPerDependant` decimal(5,2) DEFAULT NULL,
  `description` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`benefits_package`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `employee` (
  `employee_id` decimal(9,0) NOT NULL,
  `emp_password` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `address` varchar(60) DEFAULT NULL,
  `aptOrUnitNum` varchar(6) DEFAULT NULL,
  `state` varchar(2) NOT NULL,
  `zipcode` decimal(5,0) DEFAULT NULL,
  `email` varchar(55) NOT NULL,
  `phone_num` int DEFAULT NULL,
  `active_emp` enum('Active','Inactive','Hiring/Training') NOT NULL DEFAULT 'Inactive',
  `pay_type` enum('Salary','Hourly') NOT NULL,
  `marriage_status` enum('Single','Married','Married but file seperatly') NOT NULL DEFAULT 'Single',
  `job_code` int NOT NULL,
  `benefit_package` int DEFAULT NULL,
  `dependant` int DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  KEY `state` (`state`),
  KEY `benefit_package` (`benefit_package`),
  KEY `job_code` (`job_code`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`state`) REFERENCES `states` (`State`),
  CONSTRAINT `employee_ibfk_2` FOREIGN KEY (`benefit_package`) REFERENCES `benefits` (`benefits_package`),
  CONSTRAINT `employee_ibfk_3` FOREIGN KEY (`job_code`) REFERENCES `job` (`job_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `federal_tax` (
  `tax_id` int NOT NULL AUTO_INCREMENT,
  `tax_Bracket` int NOT NULL,
  `fedTax_rate` decimal(7,5) DEFAULT NULL,
  `marriage_Status` enum('Single','Married','Married but filed seperately') DEFAULT NULL,
  PRIMARY KEY (`tax_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `job` (
  `job_code` int NOT NULL,
  `job_type` varchar(30) DEFAULT NULL,
  `base_pay` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`job_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `payroll` (
  `employee_id` decimal(8,0) DEFAULT NULL,
  `base_pay` decimal(12,2) DEFAULT NULL,
  `pay_period` datetime DEFAULT NULL,
  `total_pay` decimal(12,2) DEFAULT NULL,
  KEY `employee_id` (`employee_id`),
  CONSTRAINT `payroll_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `states` (
  `State` varchar(2) NOT NULL,
  `tax_rate` decimal(7,5) NOT NULL,
  PRIMARY KEY (`State`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;