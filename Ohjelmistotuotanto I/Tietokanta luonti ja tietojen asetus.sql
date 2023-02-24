-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.6.7-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for varausjarjestelma
DROP DATABASE IF EXISTS `varausjarjestelma`;
CREATE DATABASE IF NOT EXISTS `varausjarjestelma` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `varausjarjestelma`;

-- Dumping structure for table varausjarjestelma.asiakas
DROP TABLE IF EXISTS `asiakas`;
CREATE TABLE IF NOT EXISTS `asiakas` (
  `AsiakasID` int(11) NOT NULL AUTO_INCREMENT,
  `Nimi` varchar(30) NOT NULL,
  `Yhteyshenkilo` varchar(30) DEFAULT NULL,
  `Puhelinnumero` varchar(13) NOT NULL,
  `Sahkoposti` varchar(50) NOT NULL,
  `Katuosoite` varchar(30) NOT NULL,
  `Postinumero` varchar(5) NOT NULL,
  `Postitoimipaikka` varchar(30) NOT NULL,
  `Tunnus` varchar(11) NOT NULL,
  PRIMARY KEY (`AsiakasID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table varausjarjestelma.asiakas: ~3 rows (approximately)
/*!40000 ALTER TABLE `asiakas` DISABLE KEYS */;
INSERT INTO `asiakas` (`AsiakasID`, `Nimi`, `Yhteyshenkilo`, `Puhelinnumero`, `Sahkoposti`, `Katuosoite`, `Postinumero`, `Postitoimipaikka`, `Tunnus`) VALUES
	(1, 'Pekan kuormaautot', 'Pekka Perämetsä', '045789483', 'Pekka54@gmail.com', 'Kuormakatu 5', '00100', 'Helsinki', 'Y-5454954'),
	(2, 'Liisan Meikkiklubi', 'Liisa murjunen', '054050454', 'Liisamurju@gmail.com', 'Siltakuatu 54', '60232', 'Sodankylä', 'Y-5454545'),
	(3, 'Jorman kuntoklubi', 'Jorma Uotila', '041564594', 'Jormankuntoklubi@gmail.com', 'Rollontie 5', '40032', 'Vaasa', 'Y-540222');
/*!40000 ALTER TABLE `asiakas` ENABLE KEYS */;

-- Dumping structure for table varausjarjestelma.laitteetpalvelut
DROP TABLE IF EXISTS `laitteetpalvelut`;
CREATE TABLE IF NOT EXISTS `laitteetpalvelut` (
  `LaitteetPalvelutID` int(11) NOT NULL AUTO_INCREMENT,
  `Kuvaus` varchar(400) NOT NULL,
  `Hinta` decimal(4,0) NOT NULL,
  `Nimi` varchar(30) NOT NULL,
  `Aktiivisuus` varchar(1) NOT NULL,
  `AktiivisuusMuutosPVM` date NOT NULL,
  `ToimitilaID` int(11) NOT NULL,
  PRIMARY KEY (`LaitteetPalvelutID`),
  KEY `ToimitilaID` (`ToimitilaID`),
  CONSTRAINT `laitteetpalvelut_ibfk_1` FOREIGN KEY (`ToimitilaID`) REFERENCES `toimitila` (`ToimitilaID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table varausjarjestelma.laitteetpalvelut: ~4 rows (approximately)
/*!40000 ALTER TABLE `laitteetpalvelut` DISABLE KEYS */;
INSERT INTO `laitteetpalvelut` (`LaitteetPalvelutID`, `Kuvaus`, `Hinta`, `Nimi`, `Aktiivisuus`, `AktiivisuusMuutosPVM`, `ToimitilaID`) VALUES
	(1, 'Kahvikoneella lisäät virkeyttä palavereihin', 15, 'Kahvikone', '1', '2022-04-19', 1),
	(2, 'Aamuksi valmista leipää.', 20, 'Leipäkone', '1', '2022-05-09', 1),
	(3, 'Siivoja siivoaa puhtaaksi kerran viikkoon.', 50, 'Siivoja 1 x vk', '1', '2022-05-09', 1),
	(4, 'Siivoja siivoaa puhtaaksi kahdesti viikossa. ', 90, 'Siivoja 2 x vk', '1', '2022-05-09', 1);
/*!40000 ALTER TABLE `laitteetpalvelut` ENABLE KEYS */;

-- Dumping structure for table varausjarjestelma.toimipiste
DROP TABLE IF EXISTS `toimipiste`;
CREATE TABLE IF NOT EXISTS `toimipiste` (
  `ToimipisteID` int(11) NOT NULL AUTO_INCREMENT,
  `Kaupunki` varchar(30) NOT NULL,
  `Katuosoite` varchar(30) NOT NULL,
  `Postinumero` varchar(5) NOT NULL,
  `Nimi` varchar(30) NOT NULL,
  `Postitoimipaikka` varchar(30) NOT NULL,
  `Aktiivisuus` varchar(1) NOT NULL,
  `AktiivisuusMuutosPVM` date NOT NULL,
  PRIMARY KEY (`ToimipisteID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table varausjarjestelma.toimipiste: ~2 rows (approximately)
/*!40000 ALTER TABLE `toimipiste` DISABLE KEYS */;
INSERT INTO `toimipiste` (`ToimipisteID`, `Kaupunki`, `Katuosoite`, `Postinumero`, `Nimi`, `Postitoimipaikka`, `Aktiivisuus`, `AktiivisuusMuutosPVM`) VALUES
	(1, 'Helsinki', 'Kartanonkatu 5', '00100', 'Jättitalo', 'Helsinki', '1', '2022-04-19'),
	(2, 'Joensuu', 'Länsikatu 10', '80100', 'Tiedepuisto', 'Joensuu', '1', '2022-05-09');
/*!40000 ALTER TABLE `toimipiste` ENABLE KEYS */;

-- Dumping structure for table varausjarjestelma.toimitila
DROP TABLE IF EXISTS `toimitila`;
CREATE TABLE IF NOT EXISTS `toimitila` (
  `ToimitilaID` int(11) NOT NULL AUTO_INCREMENT,
  `Nimi` varchar(30) NOT NULL,
  `Hinta` decimal(5,0) NOT NULL,
  `Henkilomaara` varchar(3) NOT NULL,
  `Aktiivisuus` varchar(1) NOT NULL,
  `AktiivisuusMuutosPVM` date NOT NULL,
  `ToimipisteID` int(11) NOT NULL,
  PRIMARY KEY (`ToimitilaID`),
  KEY `ToimipisteID` (`ToimipisteID`),
  CONSTRAINT `toimitila_ibfk_1` FOREIGN KEY (`ToimipisteID`) REFERENCES `toimipiste` (`ToimipisteID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table varausjarjestelma.toimitila: ~4 rows (approximately)
/*!40000 ALTER TABLE `toimitila` DISABLE KEYS */;
INSERT INTO `toimitila` (`ToimitilaID`, `Nimi`, `Hinta`, `Henkilomaara`, `Aktiivisuus`, `AktiivisuusMuutosPVM`, `ToimipisteID`) VALUES
	(1, 'Ullakkohuone', 130, '30', '1', '2022-04-19', 1),
	(2, 'Pöytälaatikko', 50, '2', '1', '2022-05-09', 2),
	(3, 'Kaappi', 60, '4', '1', '2022-05-09', 2),
	(4, 'TB248', 20, '3', '1', '2022-05-09', 2);
/*!40000 ALTER TABLE `toimitila` ENABLE KEYS */;

-- Dumping structure for table varausjarjestelma.varaus
DROP TABLE IF EXISTS `varaus`;
CREATE TABLE IF NOT EXISTS `varaus` (
  `VarausID` int(11) NOT NULL AUTO_INCREMENT,
  `Varaustunnus` varchar(15) NOT NULL,
  `aloitusPVM` date NOT NULL,
  `lopetusPVM` date NOT NULL,
  `Paperilasku` varchar(1) DEFAULT NULL,
  `Sahkopostilasku` varchar(1) DEFAULT NULL,
  `AsiakasID` int(11) NOT NULL,
  `ToimitilaID` int(11) NOT NULL,
  PRIMARY KEY (`VarausID`),
  KEY `AsiakasID` (`AsiakasID`),
  KEY `ToimitilaID` (`ToimitilaID`),
  CONSTRAINT `varaus_ibfk_1` FOREIGN KEY (`AsiakasID`) REFERENCES `asiakas` (`AsiakasID`),
  CONSTRAINT `varaus_ibfk_2` FOREIGN KEY (`ToimitilaID`) REFERENCES `toimitila` (`ToimitilaID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table varausjarjestelma.varaus: ~1 rows (approximately)
/*!40000 ALTER TABLE `varaus` DISABLE KEYS */;
INSERT INTO `varaus` (`VarausID`, `Varaustunnus`, `aloitusPVM`, `lopetusPVM`, `Paperilasku`, `Sahkopostilasku`, `AsiakasID`, `ToimitilaID`) VALUES
	(1, '1001', '2022-04-20', '2022-04-23', NULL, '1', 1, 1);
/*!40000 ALTER TABLE `varaus` ENABLE KEYS */;

-- Dumping structure for table varausjarjestelma.varauslaitteetpalvelut
DROP TABLE IF EXISTS `varauslaitteetpalvelut`;
CREATE TABLE IF NOT EXISTS `varauslaitteetpalvelut` (
  `VarausID` int(11) NOT NULL,
  `LaitteetPalvelutID` int(11) NOT NULL,
  PRIMARY KEY (`VarausID`,`LaitteetPalvelutID`),
  KEY `LaitteetPalvelutID` (`LaitteetPalvelutID`),
  CONSTRAINT `varauslaitteetpalvelut_ibfk_1` FOREIGN KEY (`VarausID`) REFERENCES `varaus` (`VarausID`),
  CONSTRAINT `varauslaitteetpalvelut_ibfk_2` FOREIGN KEY (`LaitteetPalvelutID`) REFERENCES `laitteetpalvelut` (`LaitteetPalvelutID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table varausjarjestelma.varauslaitteetpalvelut: ~1 rows (approximately)
/*!40000 ALTER TABLE `varauslaitteetpalvelut` DISABLE KEYS */;
INSERT INTO `varauslaitteetpalvelut` (`VarausID`, `LaitteetPalvelutID`) VALUES
	(1, 1);
/*!40000 ALTER TABLE `varauslaitteetpalvelut` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
