-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 22, 2023 at 02:21 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `podsistem2`
--

-- --------------------------------------------------------

--
-- Table structure for table `kategorija`
--

CREATE TABLE `kategorija` (
  `idKat` int(11) NOT NULL,
  `naziv` varchar(255) DEFAULT NULL,
  `roditeljska_kategorija` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kategorija`
--

INSERT INTO `kategorija` (`idKat`, `naziv`, `roditeljska_kategorija`) VALUES
(1, 'vanjos', NULL),
(2, 'vanjoss', NULL),
(3, 'vanjosss', 1),
(4, 'jona', NULL),
(5, 'jonna', 3),
(6, 'joa', NULL),
(7, 'uhah', NULL),
(8, 'uhahh', 4);

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

CREATE TABLE `korisnik` (
  `idKor` int(11) NOT NULL,
  `imePrezime` varchar(255) NOT NULL,
  `korisnickoIme` varchar(255) NOT NULL,
  `sifra` varchar(255) NOT NULL,
  `adresa` varchar(255) NOT NULL,
  `novac` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`idKor`, `imePrezime`, `korisnickoIme`, `sifra`, `adresa`, `novac`) VALUES
(1, 'vanjoza', 'vanjoza', 'vanjoza', 'vanjoza', 6979.69),
(2, 'nikola', 'jona', 'jona', 'jona', 115),
(3, 'vanjos', 'vanjy', 'vanjy', 'vanjoslavaVanjica2', 69.69),
(4, 'grgr', 'jasam', '123', 'aaaa', 312),
(5, 'lestra', 'lestra', 'strale', '1', 5),
(6, 'cmiki', 'cmiki', 'cmiki', 'bulkravljaaleks43', 15);

-- --------------------------------------------------------

--
-- Table structure for table `korpa`
--

CREATE TABLE `korpa` (
  `idKorpe` int(11) NOT NULL,
  `ukupnaCijena` float NOT NULL,
  `idKorisnika` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `korpa`
--

INSERT INTO `korpa` (`idKorpe`, `ukupnaCijena`, `idKorisnika`) VALUES
(1, 345, 1),
(2, 0, 2),
(3, 0, 3),
(4, 15, 4),
(5, 0, 5),
(6, 0, 6);

-- --------------------------------------------------------

--
-- Table structure for table `proizvod`
--

CREATE TABLE `proizvod` (
  `idPro` int(11) NOT NULL,
  `naziv` varchar(255) NOT NULL,
  `opis` text NOT NULL,
  `popust` float NOT NULL,
  `cijena` float NOT NULL,
  `kategorija_kljuc` int(11) NOT NULL,
  `prodavac` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `proizvod`
--

INSERT INTO `proizvod` (`idPro`, `naziv`, `opis`, `popust`, `cijena`, `kategorija_kljuc`, `prodavac`) VALUES
(1, 'djole', 'mazicemo ga i pazicemo ga', 50, 69, 1, 1),
(9, 'djole1', 'izvacemogadjole', 0, 2, 1, 1),
(10, 'djoleL', 'izvacemogadjole', 0, 2, 1, 1),
(11, 'lopta', 'loptasta_lopta', 50, 2, 6, 2),
(12, 'bobe', 'bobe_bombaste', 10, 10, 1, 4);

-- --------------------------------------------------------

--
-- Table structure for table `proizvod_u_korpi`
--

CREATE TABLE `proizvod_u_korpi` (
  `idKorpe` int(11) NOT NULL,
  `idProizvoda` int(11) NOT NULL,
  `kolicina` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `proizvod_u_korpi`
--

INSERT INTO `proizvod_u_korpi` (`idKorpe`, `idProizvoda`, `kolicina`) VALUES
(1, 1, 10),
(2, 10, 0),
(4, 11, 15),
(5, 10, 0),
(5, 11, 0),
(6, 10, 0),
(6, 12, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `kategorija`
--
ALTER TABLE `kategorija`
  ADD PRIMARY KEY (`idKat`),
  ADD KEY `roditeljska_kategorija` (`roditeljska_kategorija`);

--
-- Indexes for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD PRIMARY KEY (`idKor`);

--
-- Indexes for table `korpa`
--
ALTER TABLE `korpa`
  ADD PRIMARY KEY (`idKorpe`),
  ADD KEY `idKorisnika` (`idKorisnika`);

--
-- Indexes for table `proizvod`
--
ALTER TABLE `proizvod`
  ADD PRIMARY KEY (`idPro`),
  ADD KEY `kategorija_kljuc` (`kategorija_kljuc`),
  ADD KEY `prodavac` (`prodavac`);

--
-- Indexes for table `proizvod_u_korpi`
--
ALTER TABLE `proizvod_u_korpi`
  ADD PRIMARY KEY (`idKorpe`,`idProizvoda`),
  ADD KEY `idProizvoda` (`idProizvoda`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `kategorija`
--
ALTER TABLE `kategorija`
  MODIFY `idKat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `korisnik`
--
ALTER TABLE `korisnik`
  MODIFY `idKor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `korpa`
--
ALTER TABLE `korpa`
  MODIFY `idKorpe` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `proizvod`
--
ALTER TABLE `proizvod`
  MODIFY `idPro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `kategorija`
--
ALTER TABLE `kategorija`
  ADD CONSTRAINT `kategorija_ibfk_1` FOREIGN KEY (`roditeljska_kategorija`) REFERENCES `kategorija` (`idKat`);

--
-- Constraints for table `korpa`
--
ALTER TABLE `korpa`
  ADD CONSTRAINT `korpa_ibfk_1` FOREIGN KEY (`idKorisnika`) REFERENCES `korisnik` (`idKor`);

--
-- Constraints for table `proizvod`
--
ALTER TABLE `proizvod`
  ADD CONSTRAINT `proizvod_ibfk_1` FOREIGN KEY (`kategorija_kljuc`) REFERENCES `kategorija` (`idKat`),
  ADD CONSTRAINT `proizvod_ibfk_2` FOREIGN KEY (`prodavac`) REFERENCES `korisnik` (`idKor`);

--
-- Constraints for table `proizvod_u_korpi`
--
ALTER TABLE `proizvod_u_korpi`
  ADD CONSTRAINT `proizvod_u_korpi_ibfk_1` FOREIGN KEY (`idKorpe`) REFERENCES `korpa` (`idKorpe`),
  ADD CONSTRAINT `proizvod_u_korpi_ibfk_2` FOREIGN KEY (`idProizvoda`) REFERENCES `proizvod` (`idPro`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
