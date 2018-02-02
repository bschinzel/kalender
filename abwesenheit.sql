-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server Version:               10.2.11-MariaDB - mariadb.org binary distribution
-- Server Betriebssystem:        Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Exportiere Datenbank Struktur für abwesenheit
CREATE DATABASE IF NOT EXISTS `abwesenheit` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `abwesenheit`;

-- Exportiere Struktur von Tabelle abwesenheit.abwesenheit
CREATE TABLE IF NOT EXISTS `abwesenheit` (
  `uuid` char(36) NOT NULL,
  `organisationuuid` char(36) NOT NULL,
  `useruuid` char(36) NOT NULL,
  `typuuid` char(36) NOT NULL,
  `datumstart` date NOT NULL,
  `datumende` date NOT NULL,
  `genehmigt` tinyint(4) NOT NULL DEFAULT 0,
  `kommentar` varchar(200) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`organisationuuid`,`useruuid`,`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle abwesenheit.abwesenheit: ~1 rows (ungefähr)
/*!40000 ALTER TABLE `abwesenheit` DISABLE KEYS */;
INSERT INTO `abwesenheit` (`uuid`, `organisationuuid`, `useruuid`, `typuuid`, `datumstart`, `datumende`, `genehmigt`, `kommentar`, `version`) VALUES
	('1', '1', '1', '1', '2017-12-06', '2017-12-08', 1, 'KommentarTest', 0);
/*!40000 ALTER TABLE `abwesenheit` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle abwesenheit.typ
CREATE TABLE IF NOT EXISTS `typ` (
  `uuid` char(36) NOT NULL,
  `name` varchar(255) NOT NULL,
  `farbe` char(6) NOT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle abwesenheit.typ: ~0 rows (ungefähr)
/*!40000 ALTER TABLE `typ` DISABLE KEYS */;
/*!40000 ALTER TABLE `typ` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
