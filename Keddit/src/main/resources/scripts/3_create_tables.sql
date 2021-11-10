-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: keddit
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
`id` int unsigned NOT NULL,
`id_user` int unsigned NOT NULL,
`id_publications` int unsigned NOT NULL,
`photo` varchar(62) DEFAULT NULL,
`content` varchar(300) NOT NULL,
`date` datetime NOT NULL,
PRIMARY KEY (`id`),
KEY `users_comments_idx` (`id_user`),
KEY `publications_comments_idx` (`id_publications`),
CONSTRAINT `publications_comments` FOREIGN KEY (`id_publications`) REFERENCES `publications` (`id`),
CONSTRAINT `users_comments` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `communities`
--

DROP TABLE IF EXISTS `communities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `communities` (
`id` int unsigned NOT NULL,
`id_user` int unsigned NOT NULL,
`name` varchar(35) NOT NULL,
`photo` varchar(77) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `followers`
--

DROP TABLE IF EXISTS `followers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `followers` (
`id_user` int unsigned NOT NULL,
`id_community` int unsigned NOT NULL,
PRIMARY KEY (`id_user`,`id_community`),
KEY `communities_followers_idx` (`id_community`),
CONSTRAINT `communities_followers` FOREIGN KEY (`id_community`) REFERENCES `communities` (`id`),
CONSTRAINT `users_followers` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `like_comments`
--

DROP TABLE IF EXISTS `like_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `like_comments` (
`id_user` int unsigned NOT NULL,
`id_comment` int unsigned NOT NULL,
`is_like` tinyint NOT NULL,
PRIMARY KEY (`id_user`,`id_comment`),
KEY `comments_like_comments_idx` (`id_comment`),
CONSTRAINT `comments_like_comments` FOREIGN KEY (`id_comment`) REFERENCES `comments` (`id`),
CONSTRAINT `users_like_comments` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `like_publications`
--

DROP TABLE IF EXISTS `like_publications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `like_publications` (
 `id_user` int unsigned NOT NULL,
 `id_publication` int unsigned NOT NULL,
 `is_like` tinyint NOT NULL,
 PRIMARY KEY (`id_user`,`id_publication`),
 KEY `publications_like_publications_idx` (`id_publication`),
 CONSTRAINT `publications_like_publications` FOREIGN KEY (`id_publication`) REFERENCES `publications` (`id`),
 CONSTRAINT `users_like_publications` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `publications`
--

DROP TABLE IF EXISTS `publications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publications` (
`id` int unsigned NOT NULL,
`id_user` int unsigned NOT NULL,
`head` varchar(140) NOT NULL,
`body` varchar(1000) NOT NULL,
`photos` varchar(630) DEFAULT NULL,
`date` datetime NOT NULL,
`id_community` int DEFAULT NULL,
PRIMARY KEY (`id`),
KEY `users_publications_idx` (`id_user`),
CONSTRAINT `users_publications` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tags`
--

DROP TABLE IF EXISTS `tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tags` (
`id_publications` int unsigned NOT NULL,
`tag` varchar(50) NOT NULL,
PRIMARY KEY (`id_publications`,`tag`),
KEY `communities_followers_idx` (`id_publications`),
CONSTRAINT `publications_tags` FOREIGN KEY (`id_publications`) REFERENCES `publications` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
`id` int unsigned NOT NULL,
`mail` varchar(254) NOT NULL,
`password` varchar(60) NOT NULL,
`nickname` varchar(20) NOT NULL,
`date` datetime NOT NULL,
`photo` varchar(62) DEFAULT NULL,
`role` tinyint NOT NULL,
`is_banned` tinyint NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-10 21:21:54
