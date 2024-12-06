-- MySQL dump 10.13  Distrib 8.0.40, for Linux (x86_64)
--
-- Host: localhost    Database: ForEachLivraisons
-- ------------------------------------------------------
-- Server version	8.0.40-0ubuntu0.24.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `colis`
--

DROP TABLE IF EXISTS `colis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `colis` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `description` text NOT NULL,
  `poids` float NOT NULL,
  `dimensions` varchar(50) NOT NULL,
  `valeur` float NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colis`
--

LOCK TABLES `colis` WRITE;
/*!40000 ALTER TABLE `colis` DISABLE KEYS */;
INSERT INTO `colis` VALUES (6,'fusée pour aller sur la lune',50000.6,'90x2',520100),(7,'carte cadeau',5.56,'60x20',5);
/*!40000 ALTER TABLE `colis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `livraisons`
--

DROP TABLE IF EXISTS `livraisons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `livraisons` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `FK_livreur` int NOT NULL,
  `date_creation` date NOT NULL,
  `date_update` date NOT NULL,
  `date_livraison` date DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'en_attente',
  `FK_user` int NOT NULL,
  `FK_colis` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `livreur_id` (`FK_livreur`),
  KEY `livraisons_users_FK` (`FK_user`),
  KEY `livraisons_colis_FK` (`FK_colis`),
  CONSTRAINT `livraisons_colis_FK` FOREIGN KEY (`FK_colis`) REFERENCES `colis` (`ID`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `livraisons_ibfk_1` FOREIGN KEY (`FK_livreur`) REFERENCES `livreurs` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `livraisons_users_FK` FOREIGN KEY (`FK_user`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livraisons`
--

LOCK TABLES `livraisons` WRITE;
/*!40000 ALTER TABLE `livraisons` DISABLE KEYS */;
INSERT INTO `livraisons` VALUES (11,5,'2024-12-06','2024-12-06','2025-01-10','pending',4,7),(12,5,'2024-12-06','2024-12-06','2025-01-10','pending',4,7),(13,5,'2024-12-06','2024-12-06','2025-01-31','pending',4,7),(14,5,'2024-12-06','2024-12-06','2025-01-31','pending',4,7),(15,5,'2024-12-06','2024-12-06','2025-01-31','pending',4,7);
/*!40000 ALTER TABLE `livraisons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `livreurs`
--

DROP TABLE IF EXISTS `livreurs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `livreurs` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `telephone` varchar(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `email` (`email`),
  CONSTRAINT `chk_phone` CHECK (regexp_like(`telephone`,_utf8mb4'^[0-9]{10,15}$'))
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livreurs`
--

LOCK TABLES `livreurs` WRITE;
/*!40000 ALTER TABLE `livreurs` DISABLE KEYS */;
INSERT INTO `livreurs` VALUES (4,'Michel','michel','0662666007','michel@michel.com'),(5,'Pat','Pat','0662666008','pat@pat.com');
/*!40000 ALTER TABLE `livreurs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (3,'Olive','Olive','olive@olive.com'),(4,'Emily','Emily','emily@emily.com');
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

-- Dump completed on 2024-12-06 17:53:19
