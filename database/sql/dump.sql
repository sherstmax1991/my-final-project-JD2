-- MySQL dump 10.13  Distrib 5.7.21, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: credit_applications
-- ------------------------------------------------------
-- Server version	5.7.21-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `applications`
--
CREATE DATABASE credit_applications;
USE credit_applications;
DROP TABLE IF EXISTS `applications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `applications` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `quality` varchar(255) NOT NULL,
  `income` int(11) NOT NULL,
  `loan_period` int(11) NOT NULL,
  `pledge` int(11) NOT NULL,
  `scoring_resolution` varchar(255) NOT NULL,
  `sum` int(11) NOT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  `credit_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf0heeugoetqoqkdxmxl06ly6g` (`client_id`),
  KEY `FK7vmbq48b3ctvr2rjyvguca93f` (`credit_id`),
  CONSTRAINT `FK7vmbq48b3ctvr2rjyvguca93f` FOREIGN KEY (`credit_id`) REFERENCES `credits` (`id`),
  CONSTRAINT `FKf0heeugoetqoqkdxmxl06ly6g` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applications`
--

LOCK TABLES `applications` WRITE;
/*!40000 ALTER TABLE `applications` DISABLE KEYS */;
INSERT INTO `applications` (`id`, `date`, `quality`, `income`, `loan_period`, `pledge`, `scoring_resolution`, `sum`, `client_id`, `credit_id`) VALUES (1,'2018-02-01','UNKNOWN',3000,12,3000,'GOOD',4000,1,1),(2,'2018-02-02','UNKNOWN',3000,24,1500,'GOOD',2000,1,2),(3,'2018-02-03','UNKNOWN',3000,12,7000,'BAD',10000,1,1),(4,'2018-02-04','UNKNOWN',800,12,300,'GOOD',400,2,1),(5,'2018-02-05','UNKNOWN',800,24,150,'GOOD',200,2,1),(6,'2018-02-06','UNKNOWN',800,12,700,'BAD',1000,2,1),(7,'2018-02-07','UNKNOWN',800,12,300,'GOOD',400,2,2),(8,'2018-02-08','UNKNOWN',800,12,150,'GOOD',200,2,2),(9,'2018-02-09','UNKNOWN',3000,24,700,'BAD',1000,1,1),(10,'2018-02-10','UNKNOWN',600,12,300,'GOOD',400,3,2),(11,'2018-02-10','UNKNOWN',600,24,150,'GOOD',200,3,1),(12,'2018-02-10','UNKNOWN',600,18,700,'BAD',1000,3,1);
/*!40000 ALTER TABLE `applications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `children`
--

DROP TABLE IF EXISTS `children`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `children` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `birthday` date NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `children`
--

LOCK TABLES `children` WRITE;
/*!40000 ALTER TABLE `children` DISABLE KEYS */;
INSERT INTO `children` (`id`, `birthday`, `first_name`, `gender`, `last_name`) VALUES (1,'2018-06-01','Ольга','FEMALE','Тофелюк');
/*!40000 ALTER TABLE `children` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clients` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `birthday` date NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `login` varchar(255) DEFAULT NULL,
  `marital_status` varchar(255) NOT NULL,
  `rating` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_riyp540u0yca4mqdvwmf7vmbv` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` (`id`, `birthday`, `first_name`, `gender`, `last_name`, `login`, `marital_status`, `rating`) VALUES (1,'1991-12-01','Виталий','MALE','Тофелюк','kloyn','SINGLE','GOOD'),(2,'1991-01-29','Максим','MALE','Шерстобитов','sherstmax1991','SINGLE','BAD'),(3,'1964-08-27','Вацлава','FEMALE','Шерстобитова','valya','MARRIED','GOOD');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients_children`
--

DROP TABLE IF EXISTS `clients_children`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clients_children` (
  `client_id` bigint(20) NOT NULL,
  `child_id` bigint(20) NOT NULL,
  KEY `FK3i9r059i2gifimep875yju1bn` (`child_id`),
  KEY `FK86hhdlhap89jtl5uc06uuq8sm` (`client_id`),
  CONSTRAINT `FK3i9r059i2gifimep875yju1bn` FOREIGN KEY (`child_id`) REFERENCES `children` (`id`),
  CONSTRAINT `FK86hhdlhap89jtl5uc06uuq8sm` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients_children`
--

LOCK TABLES `clients_children` WRITE;
/*!40000 ALTER TABLE `clients_children` DISABLE KEYS */;
INSERT INTO `clients_children` (`client_id`, `child_id`) VALUES (1,1);
/*!40000 ALTER TABLE `clients_children` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creditId`
--

DROP TABLE IF EXISTS `credits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `credits` (
  `interest_type` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `guarantors` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `fixed_interest` double DEFAULT NULL,
  `variable_interest` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creditId`
--

LOCK TABLES `credits` WRITE;
/*!40000 ALTER TABLE `credits` DISABLE KEYS */;
INSERT INTO `credits` (`interest_type`, `id`, `guarantors`, `title`, `fixed_interest`, `variable_interest`) VALUES ('FIXED',1,0,'Standart credit',14,NULL),('VARIABLE',2,1,'Unusual credit',NULL,3);
/*!40000 ALTER TABLE `credits` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-21 21:53:59
