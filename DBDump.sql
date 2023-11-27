CREATE DATABASE  IF NOT EXISTS `quizdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `quizdb`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: quizdb
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `question` varchar(50) NOT NULL,
  `answer` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cnstr_question` (`question`)
) ENGINE=InnoDB AUTO_INCREMENT=654 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (404,'Which city is the capital of Great Britain?','London'),(452,'What is the temperature of boiling water in C?','100'),(453,'What beverage has never been drunk by an Irishman?','Water'),(502,'How often do you think about Roman Empire?','Too much for today'),(603,'Who lives in under the sea?','Sponge Bob Square Pants'),(604,'Как зовут папу?','Руслан'),(605,'Сайделька ты норм?','Да'),(606,'Где находятся Набережные Челны?','Татарстан'),(607,'Что производит КАМАЗ?','Грузовые автомобили'),(608,'На какой станции метро живет Азат?','Лесная'),(609,'Название ближайшей к солнцу планеты','Меркурий'),(610,'Вы добавлять вопросы будете?','Да'),(611,'Малика, как дела?','Супер'),(652,'Азазуент, как поживаешь?','потрясающе');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_seq`
--

DROP TABLE IF EXISTS `question_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_seq`
--

LOCK TABLES `question_seq` WRITE;
/*!40000 ALTER TABLE `question_seq` DISABLE KEYS */;
INSERT INTO `question_seq` VALUES (751);
/*!40000 ALTER TABLE `question_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_tag`
--

DROP TABLE IF EXISTS `question_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question_tag` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `question_id` int unsigned NOT NULL,
  `tag_id` int unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `questionId_idx` (`question_id`),
  KEY `tagId_idx` (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=404 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_tag`
--

LOCK TABLES `question_tag` WRITE;
/*!40000 ALTER TABLE `question_tag` DISABLE KEYS */;
INSERT INTO `question_tag` VALUES (1,1,1),(2,2,2),(3,3,2),(52,252,202),(53,252,203),(102,302,252),(152,355,302),(202,402,302),(203,403,352),(204,403,353),(205,404,354),(206,404,355),(207,404,356),(252,452,402),(253,452,403),(254,453,355),(255,453,404),(302,502,354),(303,502,452),(352,552,502),(402,602,552),(403,603,553);
/*!40000 ALTER TABLE `question_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_tag_seq`
--

DROP TABLE IF EXISTS `question_tag_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question_tag_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_tag_seq`
--

LOCK TABLES `question_tag_seq` WRITE;
/*!40000 ALTER TABLE `question_tag_seq` DISABLE KEYS */;
INSERT INTO `question_tag_seq` VALUES (501);
/*!40000 ALTER TABLE `question_tag_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score`
--

DROP TABLE IF EXISTS `score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `score` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int unsigned NOT NULL,
  `question_id` int unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `questionIdFK_idx` (`question_id`),
  KEY `userIdFK_idx` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=516 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score`
--

LOCK TABLES `score` WRITE;
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
INSERT INTO `score` VALUES (314,389142781,404),(352,389142781,453),(353,389142781,453),(354,389142781,452),(355,312259574,452),(356,312259574,453),(357,389142781,552),(358,725130385,552),(359,1067216485,404),(360,1067216485,452),(361,1067216485,404),(402,389142781,453),(403,792575828,452),(404,389142781,502),(405,792575828,452),(406,772882762,602),(407,389142781,602),(408,389142781,502),(452,1014937386,404),(453,1014937386,453),(454,1014937386,452),(455,925664312,404),(456,925664312,452),(457,389142781,604),(458,925664312,453),(459,1014937386,603),(460,1014937386,604),(461,925664312,605),(462,1014937386,605),(463,925664312,604),(464,925664312,606),(465,1014937386,608),(466,925664312,607),(467,1014937386,609),(502,1014937386,610),(503,925664312,607),(504,925664312,609),(505,925664312,604),(506,389142781,610),(507,389142781,605),(508,1014937386,611),(509,1014937386,605),(510,1014937386,652),(511,1014937386,610),(512,925664312,609),(513,1014937386,608),(514,1014937386,610),(515,1014937386,606);
/*!40000 ALTER TABLE `score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score_seq`
--

DROP TABLE IF EXISTS `score_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `score_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score_seq`
--

LOCK TABLES `score_seq` WRITE;
/*!40000 ALTER TABLE `score_seq` DISABLE KEYS */;
INSERT INTO `score_seq` VALUES (601);
/*!40000 ALTER TABLE `score_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `scoreboard`
--

DROP TABLE IF EXISTS `scoreboard`;
/*!50001 DROP VIEW IF EXISTS `scoreboard`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `scoreboard` AS SELECT 
 1 AS `userId`,
 1 AS `username`,
 1 AS `score`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `tag` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cnstr_tag` (`tag`)
) ENGINE=InnoDB AUTO_INCREMENT=554 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (553,'animation'),(356,'Capital'),(355,'England'),(354,'Geography'),(404,'London'),(402,'Physics'),(452,'Politics'),(502,'sonya'),(403,'Water'),(552,'Z');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag_seq`
--

DROP TABLE IF EXISTS `tag_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag_seq`
--

LOCK TABLES `tag_seq` WRITE;
/*!40000 ALTER TABLE `tag_seq` DISABLE KEYS */;
INSERT INTO `tag_seq` VALUES (651);
/*!40000 ALTER TABLE `tag_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int unsigned NOT NULL,
  `username` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (312259574,'Soft_halcyon'),(389142781,'azazuent'),(725130385,'fedotovStanislav'),(772882762,'NCheburator'),(792575828,'adamrashi'),(925664312,'RUSUNGAT'),(1014937386,'sai_sun_s'),(1067216485,'CrKot3');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'quizdb'
--

--
-- Final view structure for view `scoreboard`
--

/*!50001 DROP VIEW IF EXISTS `scoreboard`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `scoreboard` AS select `s`.`user_id` AS `userId`,`u`.`username` AS `username`,count(0) AS `score` from (`score` `s` join `user` `u` on((`s`.`user_id` = `u`.`id`))) group by `s`.`user_id` order by `score` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-27 12:50:36
