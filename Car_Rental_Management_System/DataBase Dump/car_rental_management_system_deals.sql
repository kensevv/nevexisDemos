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
INSERT INTO `deals` VALUES ('2020-02-20','2020-02-21','200','0134136775','0046054065','CM 7369 BA','Sofia'),('2020-03-27','2020-03-30','360','0235346135','0268721968','PB 6135 AK','Plovdiv'),('2020-01-02','2020-01-04','180','0346635401','0266845768','E 8123TX','Burgas'),('2020-04-07','2020-04-10','3000','1351343156','5236723551','CA 3618 AK','Blagoevgrad');
/*!40000 ALTER TABLE `deals` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-10 11:29:34
