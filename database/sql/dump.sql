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
) ENGINE=InnoDB AUTO_INCREMENT=2168 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applications`
--

LOCK TABLES `applications` WRITE;
/*!40000 ALTER TABLE `applications` DISABLE KEYS */;
INSERT INTO `applications` (`id`, `date`, `quality`, `income`, `loan_period`, `pledge`, `scoring_resolution`, `sum`, `client_id`, `credit_id`) VALUES (26,'2018-03-25','BAD',500,24,500,'BAD',1000,1,2),(167,'2018-03-28','GOOD',500,24,500,'BAD',1000,1,1),(1799,'2018-03-29','BAD',500,24,500,'BAD',1000,1,1),(2000,'2018-03-29','GOOD',500,24,500,'BAD',1000,1,1),(2001,'2018-03-29','GOOD',500,24,500,'BAD',1000,1,1),(2002,'2018-03-29','GOOD',500,24,500,'BAD',1000,1,1),(2167,'2018-03-29','GOOD',500,24,500,'BAD',1000,1,1);
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
  `if_real` bit(1) DEFAULT NULL,
  `last_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=418 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `children`
--

LOCK TABLES `children` WRITE;
/*!40000 ALTER TABLE `children` DISABLE KEYS */;
INSERT INTO `children` (`id`, `birthday`, `first_name`, `gender`, `if_real`, `last_name`) VALUES (417,'2018-04-03','Child','MALE','','User');
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
  `if_real` bit(1) DEFAULT NULL,
  `last_name` varchar(255) NOT NULL,
  `marital_status` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `rating` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_srdlopksdpbh4qo20au1v8w7r` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=272 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` (`id`, `birthday`, `first_name`, `gender`, `if_real`, `last_name`, `marital_status`, `password`, `rating`, `username`) VALUES (1,'1991-12-01','User','FEMALE','','User','SINGLE','$2a$04$1xjOEm2AUPV2OwpspHdPneHAwqzYDPPQV6l2ut5Xm4usEfLc5Da.u','GOOD','user'),(2,'1991-01-29','Admin','MALE','','Admin','SINGLE','$2a$04$t4I58p7XMp8W2gAfT/TQTeXp/cx9aSSMJx7j6Xv/.oa45bXMF5DOm','BAD','admin'),(3,'1964-08-27','God','FEMALE','','God','MARRIED','$2a$04$vL4RYW50dQqyWf/IuZYePuySS3hl/EG1hFRuIjZD.Ub1breYBi/He','GOOD','god');
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
INSERT INTO `clients_children` (`client_id`, `child_id`) VALUES (1,417);
/*!40000 ALTER TABLE `clients_children` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients_roles`
--

DROP TABLE IF EXISTS `clients_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clients_roles` (
  `client_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FKo54trdcx32s4eolcxktnjpc3a` (`role_id`),
  KEY `FKdfc8skx88ssraasvuujbk3kex` (`client_id`),
  CONSTRAINT `FKdfc8skx88ssraasvuujbk3kex` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`),
  CONSTRAINT `FKo54trdcx32s4eolcxktnjpc3a` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients_roles`
--

LOCK TABLES `clients_roles` WRITE;
/*!40000 ALTER TABLE `clients_roles` DISABLE KEYS */;
INSERT INTO `clients_roles` (`client_id`, `role_id`) VALUES (1,1),(2,2),(3,3);
/*!40000 ALTER TABLE `clients_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credits`
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
-- Dumping data for table `credits`
--

LOCK TABLES `credits` WRITE;
/*!40000 ALTER TABLE `credits` DISABLE KEYS */;
INSERT INTO `credits` (`interest_type`, `id`, `guarantors`, `title`, `fixed_interest`, `variable_interest`) VALUES ('FIXED',1,0,'Standart credit',14,NULL),('VARIABLE',2,1,'Unusual credit',NULL,3);
/*!40000 ALTER TABLE `credits` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exchangeRates`
--

DROP TABLE IF EXISTS `exchangeRates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exchangeRates` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `basicAmount` int(11) NOT NULL,
  `buy` double NOT NULL,
  `currency` varchar(255) NOT NULL,
  `sell` double NOT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exchangeRates`
--

LOCK TABLES `exchangeRates` WRITE;
/*!40000 ALTER TABLE `exchangeRates` DISABLE KEYS */;
INSERT INTO `exchangeRates` (`id`, `basicAmount`, `buy`, `currency`, `sell`, `version`) VALUES (1,1,1.98,'USD',2.02,33),(2,1,2.45,'EUR',2.48,11),(3,100,0.03395,'RUB',0.03455,0),(4,10,0.52,'PLN',0.58,0),(5,1,2.64,'GBP',2.74,0);
/*!40000 ALTER TABLE `exchangeRates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `first_names`
--

DROP TABLE IF EXISTS `first_names`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `first_names` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gender` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fvmlv6jddrc1tr7k1wwhi6ayd` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `first_names`
--

LOCK TABLES `first_names` WRITE;
/*!40000 ALTER TABLE `first_names` DISABLE KEYS */;
INSERT INTO `first_names` (`id`, `gender`, `name`) VALUES (1,'MALE','Александр'),(2,'FEMALE','Александра'),(3,'MALE','Алексей'),(4,'FEMALE','Алена'),(5,'FEMALE','Алина'),(6,'FEMALE','Алла'),(7,'FEMALE','Анастасия'),(8,'MALE','Анатолий'),(9,'MALE','Андрей'),(10,'FEMALE','Анна'),(11,'MALE','Антон'),(12,'FEMALE','Антонина'),(13,'MALE','Артем'),(14,'MALE','Артур'),(15,'MALE','Борис'),(16,'MALE','Вадим'),(17,'MALE','Валентин'),(18,'FEMALE','Валентина'),(19,'MALE','Валерий'),(20,'FEMALE','Валерия'),(21,'MALE','Василий'),(22,'FEMALE','Вера'),(23,'FEMALE','Вероника'),(24,'MALE','Виктор'),(25,'FEMALE','Виктория'),(26,'MALE','Виталий'),(27,'MALE','Владимир'),(28,'MALE','Владислав'),(29,'MALE','Вячеслав'),(30,'FEMALE','Галина'),(31,'MALE','Геннадий'),(32,'MALE','Георгий'),(33,'MALE','Григорий'),(34,'FEMALE','Дарья'),(35,'MALE','Денис'),(36,'FEMALE','Диана'),(37,'FEMALE','Дима'),(38,'MALE','Дмитрий'),(39,'MALE','Евгений'),(40,'FEMALE','Евгения'),(41,'FEMALE','Евдокия'),(42,'MALE','Егор'),(43,'FEMALE','Екатерина'),(44,'FEMALE','Елена'),(45,'FEMALE','Елизавета'),(46,'FEMALE','Жанна'),(47,'FEMALE','Зинаида'),(48,'FEMALE','Зоя'),(49,'MALE','Иван'),(50,'MALE','Игорь'),(51,'MALE','Илья'),(52,'FEMALE','Инна'),(53,'FEMALE','Ирина'),(54,'MALE','Кирилл'),(55,'MALE','Константин'),(56,'FEMALE','Кристина'),(57,'FEMALE','Ксения'),(58,'FEMALE','Лариса'),(59,'MALE','Леонид'),(60,'FEMALE','Лидия'),(61,'FEMALE','Лилия'),(62,'FEMALE','Любовь'),(63,'FEMALE','Людмила'),(64,'MALE','Максим'),(65,'FEMALE','Маргарита'),(66,'FEMALE','Марина'),(67,'FEMALE','Мария'),(68,'MALE','Михаил'),(69,'FEMALE','Надежда'),(70,'FEMALE','Наталия'),(71,'FEMALE','Наталья'),(72,'MALE','Никита'),(73,'MALE','Николай'),(74,'FEMALE','Нина'),(75,'FEMALE','Оксана'),(76,'MALE','Олег'),(77,'FEMALE','Олеся'),(78,'FEMALE','Ольга'),(79,'MALE','Павел'),(80,'MALE','Петр'),(81,'FEMALE','Полина'),(82,'FEMALE','Раиса'),(83,'MALE','Роман'),(84,'MALE','Руслан'),(85,'FEMALE','Светлана'),(86,'MALE','Сергей'),(87,'MALE','Станислав'),(88,'MALE','Степан'),(89,'FEMALE','Тамара'),(90,'FEMALE','Татьяна'),(91,'MALE','Федор'),(92,'MALE','Эдуард'),(93,'FEMALE','Юлия'),(94,'MALE','Юрий'),(95,'FEMALE','Яна'),(96,'MALE','Ярослав');
/*!40000 ALTER TABLE `first_names` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `last_names`
--

DROP TABLE IF EXISTS `last_names`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `last_names` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `female_equivalent` varchar(255) NOT NULL,
  `male_equivalent` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `last_names`
--

LOCK TABLES `last_names` WRITE;
/*!40000 ALTER TABLE `last_names` DISABLE KEYS */;
INSERT INTO `last_names` (`id`, `female_equivalent`, `male_equivalent`) VALUES (1,'Абрамовa','Абрамов'),(2,'Адамович','Адамович'),(3,'Александровa','Александров'),(4,'Алексеевa','Алексеев'),(5,'Андреевa','Андреев'),(6,'Антоновa','Антонов'),(7,'Афанасьевa','Афанасьев'),(8,'Барановa','Баранов'),(9,'Белевич','Белевич'),(10,'Беловa','Белов'),(11,'Беляевa','Беляев'),(12,'Богдановa','Богданов'),(13,'Богушевич','Богушевич'),(14,'Борисовa','Борисов'),(15,'Быковa','Быков'),(16,'Васильевa','Васильев'),(17,'Виноградовa','Виноградов'),(18,'Власовa','Власов'),(19,'Волковa','Волков'),(20,'Воробьёвa','Воробьёв'),(21,'Воронинa','Воронин'),(22,'Гавриловa','Гаврилов'),(23,'Герасимовa','Герасимов'),(24,'Голубевa','Голубев'),(25,'Гомола','Гомола'),(26,'Григорьевa','Григорьев'),(27,'Гусакович','Гусакович'),(28,'Гусевa','Гусев'),(29,'Давыдовa','Давыдов'),(30,'Даниловa','Данилов'),(31,'Денисовa','Денисов'),(32,'Дениченко','Дениченко'),(33,'Дмитриевa','Дмитриев'),(34,'Егоровa','Егоров'),(35,'Ефимовa','Ефимов'),(36,'Жуковa','Жуков'),(37,'Зайцевa','Зайцев'),(38,'Захарич','Захарич'),(39,'Захаровa','Захаров'),(40,'Захарченко','Захарченко'),(41,'Ивановa','Иванов'),(42,'Игнатьевa','Игнатьев'),(43,'Ильинa','Ильин'),(44,'Ищенко','Ищенко'),(45,'Карповa','Карпов'),(46,'Кирилловa','Кириллов'),(47,'Киселёвa','Киселёв'),(48,'Коваленко','Коваленко'),(49,'Ковальчук','Ковальчук'),(50,'Козловa','Козлов'),(51,'Комаровa','Комаров'),(52,'Кондратьевa','Кондратьев'),(53,'Константиновa','Константинов'),(54,'Крыловa','Крылов'),(55,'Кудрявцевa','Кудрявцев'),(56,'Кузнецовa','Кузнецов'),(57,'Кузьминa','Кузьмин'),(58,'Лебедевa','Лебедев'),(59,'Леонтьевa','Леонтьев'),(60,'Львовa','Львов'),(61,'Макаровa','Макаров'),(62,'Максимовa','Максимов'),(63,'Марковa','Марков'),(64,'Мартыновa','Мартынов'),(65,'Матвеевa','Матвеев'),(66,'Мельник','Мельник'),(67,'Миллерa','Миллер'),(68,'Мироновa','Миронов'),(69,'Михайловa','Михайлов'),(70,'Морозовa','Морозов'),(71,'Мосейчук','Мосейчук'),(72,'Назаровa','Назаров'),(73,'Наумовa','Наумов'),(74,'Никитинa','Никитин'),(75,'Никифоровa','Никифоров'),(76,'Николаевa','Николаев'),(77,'Новиковa','Новиков'),(78,'Омельченко','Омельченко'),(79,'Омельянович','Омельянович'),(80,'Орловa','Орлов'),(81,'Осиповa','Осипов'),(82,'Павловa','Павлов'),(83,'Петренко','Петренко'),(84,'Петрикович','Петрикович'),(85,'Петровa','Петров'),(86,'Пивень','Пивень'),(87,'Поляковa','Поляков'),(88,'Пономарёвa','Пономарёв'),(89,'Поповa','Попов'),(90,'Прокофьевa','Прокофьев'),(91,'Романовa','Романов'),(92,'Савельевa','Савельев'),(93,'Семёновa','Семёнов'),(94,'Сергеевa','Сергеев'),(95,'Сидоренко','Сидоренко'),(96,'Сидоровa','Сидоров'),(97,'Смирновa','Смирнов'),(98,'Соболевa','Соболев'),(99,'Сокирко','Сокирко'),(100,'Соколовa','Соколов'),(101,'Соловьёвa','Соловьёв'),(102,'Сорокинa','Сорокин'),(103,'Степановa','Степанов'),(104,'Тимофеевa','Тимофеев'),(105,'Титовa','Титов'),(106,'Тихомировa','Тихомиров'),(107,'Тоут','Тоут'),(108,'Тофелюк','Тофелюк'),(109,'Троицкая','Троицкий'),(110,'Трофимовa','Трофимов'),(111,'Ушаковa','Ушаков'),(112,'Федорович','Федорович'),(113,'Федотовa','Федотов'),(114,'Филипповa','Филиппов'),(115,'Фоминa','Фомин'),(116,'Фроловa','Фролов'),(117,'Фёдоровa','Фёдоров'),(118,'Чистяковa','Чистяков'),(119,'Шапук','Шапук'),(120,'Шмидт','Шмидт'),(121,'Шульцa','Шульц'),(122,'Щербаковa','Щербаков'),(123,'Яковлевa','Яковлев');
/*!40000 ALTER TABLE `last_names` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`id`, `name`) VALUES (1,'USER'),(2,'ADMIN'),(3,'GOD');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-03 18:17:53
