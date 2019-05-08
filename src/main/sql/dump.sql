-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: warsztat6
-- ------------------------------------------------------
-- Server version	5.7.26-0ubuntu0.18.10.1

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
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `text` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tweet_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2occdyecvmxwqjm9eu28a9x3n` (`tweet_id`),
  KEY `FK8omq0tc18jd43bu5tjh6jvraq` (`user_id`),
  CONSTRAINT `FK2occdyecvmxwqjm9eu28a9x3n` FOREIGN KEY (`tweet_id`) REFERENCES `tweets` (`id`),
  CONSTRAINT `FK8omq0tc18jd43bu5tjh6jvraq` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,'2019-05-03 23:35:02','comm1',8,1),(2,'2019-05-03 23:36:37','comm2',8,1),(3,'2019-05-03 23:40:31','comm3',8,1),(4,'2019-05-03 23:41:56','comm1',7,1),(6,'2019-05-03 23:44:24','comm1',7,2),(9,'2019-05-04 00:12:52','comm1',8,1),(11,'2019-05-04 00:13:04','tweet2',8,1),(12,'2019-05-04 00:19:04','comm1',8,1),(13,'2019-05-04 00:28:08','tweet3',8,1),(14,'2019-05-04 00:28:53','comm1',8,2),(15,'2019-05-04 00:43:50','comm1',5,2),(16,'2019-05-04 00:45:56','comm1',8,1),(17,'2019-05-04 00:52:24','tweet1',8,1),(18,'2019-05-04 09:51:08','tweet2',8,1),(19,'2019-05-04 09:51:36','tweet1',8,1),(20,'2019-05-05 12:33:29','comm1',10,1),(21,'2019-05-05 12:33:32','comm2',10,1);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `isRead` bit(1) NOT NULL,
  `text` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `receiver_id` bigint(20) DEFAULT NULL,
  `sender_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt05r0b6n0iis8u7dfna4xdh73` (`receiver_id`),
  KEY `FK4ui4nnwntodh6wjvck53dbk9m` (`sender_id`),
  CONSTRAINT `FK4ui4nnwntodh6wjvck53dbk9m` FOREIGN KEY (`sender_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKt05r0b6n0iis8u7dfna4xdh73` FOREIGN KEY (`receiver_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (1,'2019-05-05 14:21:51',_binary '','message1',2,1),(2,'2019-05-05 14:22:30',_binary '\0','message2',2,1),(3,'2019-05-05 14:24:21',_binary '','message1',1,2),(5,'2019-05-05 16:04:09',_binary '\0','amet erat nulla tempus vivamus in felis eu sapien cursus vestibulum proin eu mi nulla ac enim in tempor turpis nec euismod scelerisque quam turpis adipiscing lorem vitae',1,2),(6,'2019-05-05 16:04:55',_binary '\0','fusce consequat nulla nisl nunc nisl duis bibendum felis sed interdum venenatis turpis enim blandit mi in porttitor pede justo eu',1,2),(7,'2019-05-05 16:08:12',_binary '\0','rhoncus aliquet pulvinar sed nisl nunc rhoncus dui vel sem sed sagittis nam congue risus semper porta volutpat quam pede lobortis ligula sit amet eleifend pede libero quis orci nullam molestie nibh in lectus',1,2),(8,'2019-05-05 16:17:26',_binary '','nulla ultrices aliquet maecenas leo odio condimentum id luctus nec molestie sed justo pellentesque viverra pede ac diam cras pellentesque volutpat dui maecenas tristique est et tempus semper est quam pharetra magna ac consequat metus sapien ut',2,1),(9,'2019-05-05 16:22:12',_binary '','platea dictumst morbi vestibulum velit id pretium iaculis diam erat fermentum justo nec condimentum neque sapien placerat ante nulla justo aliquam quis turpis eget elit sodales scelerisque mauris sit',2,1),(10,'2019-05-07 09:27:55',_binary '','test1',2,1),(11,'2019-05-07 09:57:16',_binary '\0','New message',1,3),(12,'2019-05-07 09:57:27',_binary '\0','New message',2,3),(13,'2019-05-07 09:59:21',_binary '\0','New message',3,1),(14,'2019-05-07 10:57:24',_binary '\0','New message',3,1);
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tweets`
--

DROP TABLE IF EXISTS `tweets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tweets` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `text` varchar(140) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgclwpsnjft4s6umfjopgcp051` (`user_id`),
  CONSTRAINT `FKgclwpsnjft4s6umfjopgcp051` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tweets`
--

LOCK TABLES `tweets` WRITE;
/*!40000 ALTER TABLE `tweets` DISABLE KEYS */;
INSERT INTO `tweets` VALUES (1,'2019-04-30 11:29:30','tweet1',1),(2,'2019-04-30 11:29:48','tweet2',1),(3,'2019-05-02 19:07:46','tweet3',1),(4,'2019-05-02 19:22:29','tweet4',1),(5,'2019-05-02 19:26:10','tweet2',2),(6,'2019-05-02 19:26:20','tweet3',2),(7,'2019-05-03 21:44:34','tweet5',1),(8,'2019-05-03 23:11:07','tweet6',1),(9,'2019-05-04 09:52:05','tweet3',1),(10,'2019-05-04 09:58:31','comm2',1),(11,'2019-05-05 13:22:12','tweet4',1),(12,'2019-05-07 09:51:23','New tweet',2),(14,'2019-05-07 09:56:59','New tweet',3);
/*!40000 ALTER TABLE `tweets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `firstName` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `lastName` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'email@test','First12','Last123','$2a$10$pZRtmT03gkff6t4mV0b3MOZTraaTf8CfNfqkYI9t/oQDujqc00zj.'),(2,'email12@test','First21','Last2','$2a$10$D.s.SYHlnCS61WugGMsC1O4PSK0ALy9PkreqyVMMa3XYZuJLH5072'),(3,'email1@test','New1','New1','$2a$10$D.s.SYHlnCS61WugGMsC1O4PSK0ALy9PkreqyVMMa3XYZuJLH5072'),(9,'email@test1','fsdfsdf','sdfsdf','$2a$10$D.s.SYHlnCS61WugGMsC1O4PSK0ALy9PkreqyVMMa3XYZuJLH5072');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-07 13:00:34
