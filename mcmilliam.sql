CREATE DATABASE  IF NOT EXISTS `mcmilliam` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mcmilliam`;
-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mcmilliam
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance` (
  `date` datetime DEFAULT CURRENT_TIMESTAMP,
  `clock_in` timestamp NULL DEFAULT NULL,
  `clock_out` timestamp NULL DEFAULT NULL,
  `emp_id` decimal(9,0) NOT NULL,
  KEY `emp_id` (`emp_id`),
  CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `benefits`
--

DROP TABLE IF EXISTS `benefits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `benefits` (
  `benefits_package` int NOT NULL AUTO_INCREMENT,
  `cost` decimal(6,2) NOT NULL,
  `costPerDependant` decimal(5,2) DEFAULT NULL,
  `description` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`benefits_package`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `benefits`
--

LOCK TABLES `benefits` WRITE;
/*!40000 ALTER TABLE `benefits` DISABLE KEYS */;
INSERT INTO `benefits` VALUES (1,69.17,50.00,'Health and dental/vision');
/*!40000 ALTER TABLE `benefits` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emp_benefits`
--

DROP TABLE IF EXISTS `emp_benefits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emp_benefits` (
  `package` int DEFAULT NULL,
  `emp_id` decimal(9,0) DEFAULT NULL,
  `numof_dependants` int NOT NULL,
  KEY `package` (`package`),
  KEY `emp_id` (`emp_id`),
  CONSTRAINT `emp_benefits_ibfk_1` FOREIGN KEY (`package`) REFERENCES `benefits` (`benefits_package`),
  CONSTRAINT `emp_benefits_ibfk_2` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp_benefits`
--

LOCK TABLES `emp_benefits` WRITE;
/*!40000 ALTER TABLE `emp_benefits` DISABLE KEYS */;
/*!40000 ALTER TABLE `emp_benefits` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emp_job`
--

DROP TABLE IF EXISTS `emp_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emp_job` (
  `emp_id` decimal(9,0) NOT NULL,
  `salary` decimal(12,2) NOT NULL,
  `dept` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`salary`),
  KEY `dept` (`dept`),
  CONSTRAINT `emp_job_ibfk_2` FOREIGN KEY (`dept`) REFERENCES `job` (`dept`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp_job`
--

LOCK TABLES `emp_job` WRITE;
/*!40000 ALTER TABLE `emp_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `emp_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emp_security`
--

DROP TABLE IF EXISTS `emp_security`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emp_security` (
  `emp_id` decimal(9,0) DEFAULT NULL,
  `question_id` int DEFAULT NULL,
  `answer` varchar(45) DEFAULT NULL,
  KEY `emp_id` (`emp_id`),
  KEY `question_id` (`question_id`),
  CONSTRAINT `emp_security_ibfk_1` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`employee_id`),
  CONSTRAINT `emp_security_ibfk_2` FOREIGN KEY (`question_id`) REFERENCES `sec_question` (`Question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp_security`
--

LOCK TABLES `emp_security` WRITE;
/*!40000 ALTER TABLE `emp_security` DISABLE KEYS */;
/*!40000 ALTER TABLE `emp_security` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emp_training`
--

DROP TABLE IF EXISTS `emp_training`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emp_training` (
  `training_id` int DEFAULT NULL,
  `emp_id` decimal(9,0) DEFAULT NULL,
  `status` enum('Incomplete','In Progress','Completed') DEFAULT NULL,
  KEY `training_id` (`training_id`),
  KEY `emp_id` (`emp_id`),
  CONSTRAINT `emp_training_ibfk_1` FOREIGN KEY (`training_id`) REFERENCES `training` (`id`),
  CONSTRAINT `emp_training_ibfk_2` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp_training`
--

LOCK TABLES `emp_training` WRITE;
/*!40000 ALTER TABLE `emp_training` DISABLE KEYS */;
/*!40000 ALTER TABLE `emp_training` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
  `manager` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`employee_id`),
  KEY `state` (`state`),
  KEY `Username` (`email`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`state`) REFERENCES `states` (`State`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (224537,'password1#','Frank','Man','555 Main St','apt 5','Tx',79401,'FranktheTankMan@gmail.com',800955433,'Active','Salary',0);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `federal_tax`
--

DROP TABLE IF EXISTS `federal_tax`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `federal_tax` (
  `tax_id` int NOT NULL AUTO_INCREMENT,
  `tax_Bracket` int NOT NULL,
  `fedTax_rate` decimal(7,5) DEFAULT NULL,
  `marriage_Status` enum('Single','Married','Married but filed seperately') DEFAULT NULL,
  PRIMARY KEY (`tax_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `federal_tax`
--

LOCK TABLES `federal_tax` WRITE;
/*!40000 ALTER TABLE `federal_tax` DISABLE KEYS */;
/*!40000 ALTER TABLE `federal_tax` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job` (
  `job_code` int DEFAULT NULL,
  `job_type` varchar(30) DEFAULT NULL,
  `dept` varchar(25) NOT NULL,
  PRIMARY KEY (`dept`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payroll`
--

DROP TABLE IF EXISTS `payroll`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payroll` (
  `employee_id` decimal(8,0) DEFAULT NULL,
  `total_pay` decimal(12,2) DEFAULT NULL,
  `salary` decimal(12,2) DEFAULT NULL,
  `state_taxrate` decimal(7,5) DEFAULT NULL,
  KEY `employee_id` (`employee_id`),
  KEY `payroll_ibfk_2_idx` (`salary`),
  KEY `payroll_ibfk_3_idx` (`state_taxrate`),
  CONSTRAINT `payroll_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`),
  CONSTRAINT `payroll_ibfk_2` FOREIGN KEY (`salary`) REFERENCES `emp_job` (`salary`),
  CONSTRAINT `payroll_ibfk_3` FOREIGN KEY (`state_taxrate`) REFERENCES `states` (`tax_rate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payroll`
--

LOCK TABLES `payroll` WRITE;
/*!40000 ALTER TABLE `payroll` DISABLE KEYS */;
/*!40000 ALTER TABLE `payroll` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `performance`
--

DROP TABLE IF EXISTS `performance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `performance` (
  `emp_id` decimal(9,0) DEFAULT NULL,
  `manager_id` int DEFAULT NULL,
  `manager_score` enum('1','2','3','4','5','6','7','8','9','10') DEFAULT NULL,
  `customer_score` enum('1','2','3','4','5','6','7','8','9','10') DEFAULT NULL,
  KEY `emp_id` (`emp_id`),
  CONSTRAINT `performance_ibfk_1` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `performance`
--

LOCK TABLES `performance` WRITE;
/*!40000 ALTER TABLE `performance` DISABLE KEYS */;
/*!40000 ALTER TABLE `performance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule` (
  `workdays` date NOT NULL,
  `payday` tinyint(1) DEFAULT '0',
  `emp_id` decimal(9,0) DEFAULT NULL,
  PRIMARY KEY (`workdays`),
  KEY `emp_id` (`emp_id`),
  CONSTRAINT `schedule_ibfk_1` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sec_question`
--

DROP TABLE IF EXISTS `sec_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sec_question` (
  `Question_id` int NOT NULL AUTO_INCREMENT,
  `Description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sec_question`
--

LOCK TABLES `sec_question` WRITE;
/*!40000 ALTER TABLE `sec_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `sec_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `states`
--

DROP TABLE IF EXISTS `states`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `states` (
  `State` varchar(2) NOT NULL,
  `tax_rate` decimal(7,5) NOT NULL,
  PRIMARY KEY (`State`),
  KEY `state_taxrate` (`tax_rate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `states`
--

LOCK TABLES `states` WRITE;
/*!40000 ALTER TABLE `states` DISABLE KEYS */;
INSERT INTO `states` VALUES ('Tx',0.08250);
/*!40000 ALTER TABLE `states` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `training`
--

DROP TABLE IF EXISTS `training`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `training` (
  `id` int NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `training`
--

LOCK TABLES `training` WRITE;
/*!40000 ALTER TABLE `training` DISABLE KEYS */;
/*!40000 ALTER TABLE `training` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(30) NOT NULL,
  `pass` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `Username` (`username`),
  CONSTRAINT `user_username_1` FOREIGN KEY (`username`) REFERENCES `employee` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'mcmilliam'
--

--
-- Dumping routines for database 'mcmilliam'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-28 22:17:31
