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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-10 11:29:34
