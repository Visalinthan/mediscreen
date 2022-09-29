-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3309
-- Généré le :  jeu. 29 sep. 2022 à 11:10
-- Version du serveur :  10.4.10-MariaDB
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `mediscreen`
--

-- --------------------------------------------------------

--
-- Structure de la table `medecin`
--

DROP TABLE IF EXISTS `medecin`;
CREATE TABLE IF NOT EXISTS `medecin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `medecin`
--

INSERT INTO `medecin` (`id`, `first_name`, `last_name`) VALUES
(1, 'Rene', 'JEAN');

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

DROP TABLE IF EXISTS `patient`;
CREATE TABLE IF NOT EXISTS `patient` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone` int(11) NOT NULL,
  `sex` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `patient`
--

INSERT INTO `patient` (`id`, `address`, `dob`, `first_name`, `last_name`, `phone`, `sex`) VALUES
(10, '2 Warren Street ', '1968-06-22', 'Lucas', 'test', 145789658, 'M'),
(11, '745 West Valley Farms Drive', '1952-09-27', 'Pippa', 'Rees', 612507543, 'F'),
(12, '599 East Garden Ave ', '1952-11-11', 'Edward ', 'Arnold ', 612507543, 'M'),
(13, '894 Hall Street', '1946-11-26', 'Anthony', 'Sharp ', 313515646, 'M'),
(14, '4 Southampton Road ', '1958-06-29', 'Wendy ', 'Ince ', 577678774, 'F'),
(15, '40 Sulphur Springs Dr', '1949-12-07', 'Tracey', 'Ross', 265656544, 'F'),
(16, '12 Cobblestone St ', '1966-12-31', 'Claire', 'Wilson', 131354684, 'F'),
(17, '193 Vale St ', '1945-06-24', 'Max ', 'Buckland ', 574575788, 'M'),
(18, '12 Beechwood Road', '1964-06-18', 'Natalie ', 'Clark ', 87785677, 'F'),
(19, '1202 Bumble Dr ', '1959-06-28', 'Piers ', 'Bailey ', 874864665, 'M'),
(42, '9 allée d\'anjou', '1995-07-07', 'Bernard', 'Louis', 612507543, 'M');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
