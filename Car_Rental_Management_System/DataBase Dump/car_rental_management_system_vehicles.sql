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
INSERT INTO `vehicles` VALUES ('BD 8888 OP','Tesla',1,10000,'BULSTRAT-642678',1500,'Blagoevgrad'),('CA 3618 AK','Ford',1,3051,'UNIKA-642517',1000,'Blagoevgrad'),('CB 2516 KT','Ferarri',1,1050,'LEVINS-642647',500,'Smolqn'),('CM 1351 AK','VW',1,200514,'DSK-91415161',100,'Sofia'),('CM 7369 BA','Mercedes',1,10519,'DZI-531516',200,'Sofia'),('E 4561 PA','Toyota',1,125999,'DSK-5316891',60,'Burgas'),('E 8123TX','BMW',1,10559,'EVROINS-513951',90,'Burgas'),('PB 5167 PE','Opel',1,320000,'BULSTRAT-513616',50,'Plovdiv'),('PB 6135 AK','Audi',1,51951,'LEVINS-215166',120,'Plovdiv'),('PZ 0616 YA','PEJO',1,81595,'DSK-613617',30,'Smolqn'),('testADD','alo',0,111111,'alo',111111,'Plovdiv');
/*!40000 ALTER TABLE `vehicles` ENABLE KEYS */;
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
