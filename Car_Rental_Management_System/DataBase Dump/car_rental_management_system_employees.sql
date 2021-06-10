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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-10 11:29:35
