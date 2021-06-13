-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: car_rental_management_system
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounts` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `employee_id` varchar(50) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `accountscol_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `FK_ACC_EMPL` (`employee_id`),
  CONSTRAINT `FK_ACC_EMPL` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (1,'kensev','kensev2000@gmail.com','test','0046054065','ADMIN'),(2,'test','test','test',NULL,'ADMIN');
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `branches`
--

DROP TABLE IF EXISTS `branches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branches` (
  `name` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `manager_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branches`
--

LOCK TABLES `branches` WRITE;
/*!40000 ALTER TABLE `branches` DISABLE KEYS */;
INSERT INTO `branches` VALUES ('Blagoevgrad','ul. Kukush 49','0046054065'),('Burgas','bul Ruski 50',NULL),('Plovdiv','doktor Vlado 44','0268721968'),('Smolqn','bul Vasil Aprilov 9','0346347968'),('Sofia','bul. Bulgaria 129','0046054065');
/*!40000 ALTER TABLE `branches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientemployee`
--

DROP TABLE IF EXISTS `clientemployee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientemployee` (
  `ID` int NOT NULL,
  `first_name` varchar(15) NOT NULL,
  `last_name` varchar(15) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(13) NOT NULL,
  `birthday` date NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `CK_CLEMP_VALID_BDAY` CHECK ((`birthday` > _utf8mb4'1990-01-01')),
  CONSTRAINT `CK_CLEMP_VALID_ID` CHECK ((length(`ID`) = 10))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientemployee`
--

LOCK TABLES `clientemployee` WRITE;
/*!40000 ALTER TABLE `clientemployee` DISABLE KEYS */;
/*!40000 ALTER TABLE `clientemployee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clients` (
  `ID` char(10) NOT NULL,
  `first_name` varchar(15) NOT NULL,
  `last_name` varchar(15) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(13) NOT NULL,
  `birthday` date NOT NULL,
  `driver_lic` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `CK_CL_VALID_BDAY` CHECK ((`birthday` > _utf8mb4'1990-01-01')),
  CONSTRAINT `CK_CL_VALID_ID` CHECK ((length(`ID`) = 10))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES ('0046054065','Kenan','Yusein','kensev2000@gmail.com','0876617490','2000-06-05','56489920'),('0048729563','Martin','Boni','marto_belqta@gmail.com','0887461792','1999-02-23','56489920'),('0134136775','Iliana','Lichkova','lichkova07@gmail.com','0874983168','2001-09-23','12312520'),('0235346135','Ivan','Tsonev','i.tsonev@gmail.com','0888567134','1998-10-17','56486346'),('0346635401','Hristo','Hristov','icaka@abv.bg','0894571349','2000-08-01','54612020'),('1351343156','Filip','Yordanov','filqka@gmail.com','0899571395','1995-12-13','146689720');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_us`
--

DROP TABLE IF EXISTS `contact_us`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contact_us` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `message` mediumtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_us`
--

LOCK TABLES `contact_us` WRITE;
/*!40000 ALTER TABLE `contact_us` DISABLE KEYS */;
INSERT INTO `contact_us` VALUES (1,'Martin Hristov','Marto@abv.bg','What would your best offer be for the Ford Mustang if i took it for a longer period of time for example 2 weeks?');
/*!40000 ALTER TABLE `contact_us` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deals`
--

DROP TABLE IF EXISTS `deals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deals` (
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `payment` varchar(50) DEFAULT NULL,
  `client_id` varchar(50) NOT NULL,
  `employee_id` varchar(50) NOT NULL,
  `vehicle_licPlate` varchar(50) NOT NULL,
  `branch_name` varchar(50) NOT NULL,
  PRIMARY KEY (`client_id`,`employee_id`,`vehicle_licPlate`,`branch_name`,`start_date`),
  KEY `FK_DEALS_EMPLOYEEID` (`employee_id`),
  KEY `FK_DEALS_VEHLIC` (`vehicle_licPlate`),
  KEY `FK_DEALS_BRANCHNAME` (`branch_name`),
  CONSTRAINT `FK_DEALS_BRANCHNAME` FOREIGN KEY (`branch_name`) REFERENCES `branches` (`name`),
  CONSTRAINT `FK_DEALS_CLIENTID` FOREIGN KEY (`client_id`) REFERENCES `clients` (`ID`),
  CONSTRAINT `FK_DEALS_EMPLOYEEID` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`ID`),
  CONSTRAINT `FK_DEALS_VEHLIC` FOREIGN KEY (`vehicle_licPlate`) REFERENCES `vehicles` (`license_plate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deals`
--

LOCK TABLES `deals` WRITE;
/*!40000 ALTER TABLE `deals` DISABLE KEYS */;
INSERT INTO `deals` VALUES ('2021-06-10','2021-06-11','1111.0','0046054065','0046054065','BD 8888 OP','Plovdiv'),('2020-02-20','2020-02-21','200','0134136775','0046054065','CM 7369 BA','Sofia'),('2020-03-27','2020-03-30','360','0235346135','0268721968','PB 6135 AK','Plovdiv'),('2020-01-02','2020-01-04','180','0346635401','0266845768','E 8123TX','Burgas'),('2020-04-07','2020-04-10','3000','1351343156','5236723551','CA 3618 AK','Blagoevgrad');
/*!40000 ALTER TABLE `deals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `ID` varchar(50) NOT NULL,
  `first_name` varchar(15) NOT NULL,
  `last_name` varchar(15) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(13) NOT NULL,
  `birthday` date NOT NULL,
  `work_number` varchar(50) NOT NULL,
  `branch_name` varchar(50) NOT NULL,
  `manager_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_BRANCHNAME_BRANCH` (`branch_name`),
  KEY `FK_MANAGERID_EMPLOYEE` (`manager_id`),
  CONSTRAINT `FK_BRANCHNAME_BRANCH` FOREIGN KEY (`branch_name`) REFERENCES `branches` (`name`),
  CONSTRAINT `FK_MANAGERID_EMPLOYEE` FOREIGN KEY (`manager_id`) REFERENCES `employees` (`ID`),
  CONSTRAINT `CK_EMP_VALID_BDAY` CHECK ((`birthday` > _utf8mb4'1990-01-01')),
  CONSTRAINT `CK_EMP_VALID_ID` CHECK ((length(`ID`) = 10))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES ('0046054065','Kenan','Yusein','kensev2000@gmail.com','0876617490','2000-06-05','1A','Sofia',NULL),('0266845768','Borkata','Purvi','purvaka@gmail.com','0856879591','1998-05-16','515K','Burgas','0046054065'),('0268721968','Tsvetelin','Sotirov','ceci@gmail.com','0854587491','1999-04-30','21B','Plovdiv','0046054065'),('0346347968','Atanas','Kolev','nakata@gmail.com','0854967491','1997-11-16','61H','Smolqn','0046054065'),('5236723551','Dimitar','Bedachev','mitakaa@gmail.com','0846257951','1998-12-23','51C','Blagoevgrad','0046054065');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requires`
--

DROP TABLE IF EXISTS `requires`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `requires` (
  `rdate` date NOT NULL,
  `client_id` varchar(50) NOT NULL,
  `branch_name` varchar(50) NOT NULL,
  `model` varchar(50) DEFAULT NULL,
  `max_price` double DEFAULT NULL,
  PRIMARY KEY (`rdate`,`client_id`,`branch_name`),
  KEY `FK_REQUIRES_CLIENTID` (`client_id`),
  KEY `FK_REQUIRES_BRANCHNAME` (`branch_name`),
  CONSTRAINT `FK_REQUIRES_BRANCHNAME` FOREIGN KEY (`branch_name`) REFERENCES `branches` (`name`),
  CONSTRAINT `FK_REQUIRES_CLIENTID` FOREIGN KEY (`client_id`) REFERENCES `clients` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requires`
--

LOCK TABLES `requires` WRITE;
/*!40000 ALTER TABLE `requires` DISABLE KEYS */;
INSERT INTO `requires` VALUES ('2021-06-10','0134136775','Plovdiv','Audi',2000),('2021-06-10','0235346135','Sofia','Mercedes',5000),('2021-06-10','0346635401','Burgas','BMW',1500),('2021-06-10','1351343156','Blagoevgrad','Ford',2600);
/*!40000 ALTER TABLE `requires` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicles`
--

DROP TABLE IF EXISTS `vehicles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicles` (
  `license_plate` varchar(10) NOT NULL,
  `model` varchar(50) NOT NULL,
  `is_available` int DEFAULT '1',
  `mileage` int DEFAULT '0',
  `insurance` varchar(50) NOT NULL,
  `price` double NOT NULL,
  `branch_name` varchar(50) NOT NULL,
  PRIMARY KEY (`license_plate`),
  KEY `FK_BRANCHNAME_BRANCHES` (`branch_name`),
  CONSTRAINT `FK_BRANCHNAME_BRANCHES` FOREIGN KEY (`branch_name`) REFERENCES `branches` (`name`),
  CONSTRAINT `CK_VEH_AVAILABLE` CHECK (((`is_available` = 0) or (`is_available` = 1))),
  CONSTRAINT `CK_VEH_MILEAGE` CHECK ((`mileage` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicles`
--

LOCK TABLES `vehicles` WRITE;
/*!40000 ALTER TABLE `vehicles` DISABLE KEYS */;
INSERT INTO `vehicles` VALUES ('123456789','model1',1,1000,'insurance1',100,'Sofia'),('BD 8888 OP','Tesla',1,10000,'BULSTRAT-642678',1500,'Blagoevgrad'),('CA 3618 AK','Ford',1,3051,'UNIKA-642517',1000,'Blagoevgrad'),('CB 2516 KT','Ferarri',1,1050,'LEVINS-642647',500,'Smolqn'),('CM 1351 AK','VW',1,200514,'DSK-91415161',100,'Sofia'),('CM 7369 BA','Mercedes',1,10519,'DZI-531516',200,'Sofia'),('E 4561 PA','Toyota',1,125999,'DSK-5316891',60,'Burgas'),('E 8123TX','BMW',1,10559,'EVROINS-513951',90,'Burgas'),('PB 5167 PE','Opel',1,320000,'BULSTRAT-513616',50,'Plovdiv'),('PB 6135 AK','Audi',1,51951,'LEVINS-215166',120,'Plovdiv'),('PZ 0616 YA','PEJO',1,81595,'DSK-613617',30,'Smolqn'),('testADD','TEST',0,0,'0',1,'Sofia');
/*!40000 ALTER TABLE `vehicles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'car_rental_management_system'
--

--
-- Dumping routines for database 'car_rental_management_system'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-13 17:15:42
