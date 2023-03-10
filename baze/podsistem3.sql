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
-- Database: `podsistem3`
--

-- --------------------------------------------------------

--
-- Table structure for table `korisnici`
--

CREATE TABLE `korisnici` (
  `idKor` int(11) NOT NULL,
  `korisnickoIme` varchar(255) NOT NULL,
  `lozinka` varchar(255) NOT NULL,
  `imePrezime` varchar(255) NOT NULL,
  `novac` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `korisnici`
--

INSERT INTO `korisnici` (`idKor`, `korisnickoIme`, `lozinka`, `imePrezime`, `novac`) VALUES
(1, 'vanjoza', 'vanjoza', 'vanjoza', 6979.69),
(2, 'jona', 'jona', 'nikola', 130),
(3, 'vanjy', 'vanjy', 'vanjos', 69.69),
(4, 'jasam', '123', 'grgr', 312),
(5, 'lestra', 'strale', 'lestra', 5),
(6, 'cmiki', 'cmiki', 'cmiki', 15);

-- --------------------------------------------------------

--
-- Table structure for table `korpe`
--

CREATE TABLE `korpe` (
  `idKorp` int(11) NOT NULL,
  `id_korisnika` int(11) NOT NULL,
  `ukupna_cijena` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `korpe`
--

INSERT INTO `korpe` (`idKorp`, `id_korisnika`, `ukupna_cijena`) VALUES
(1, 1, 345),
(2, 2, 0),
(3, 3, 0),
(4, 4, 15),
(5, 5, 0),
(6, 6, 0);

-- --------------------------------------------------------

--
-- Table structure for table `narudzbine`
--

CREATE TABLE `narudzbine` (
  `idNar` int(11) NOT NULL,
  `ukupnaCijena` float DEFAULT NULL,
  `datumVrijeme` datetime DEFAULT NULL,
  `adresaDostave` varchar(255) DEFAULT NULL,
  `gradDostave` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `narudzbine`
--

INSERT INTO `narudzbine` (`idNar`, `ukupnaCijena`, `datumVrijeme`, `adresaDostave`, `gradDostave`) VALUES
(1, 0, '2023-02-21 23:17:53', 'a', 'a'),
(2, 15, '2023-02-21 23:29:39', 'testara', 'kevara'),
(3, 100, '2023-02-22 01:10:23', 'majstor', 'majstorovic');

-- --------------------------------------------------------

--
-- Table structure for table `proizvodi`
--

CREATE TABLE `proizvodi` (
  `idPro` int(11) NOT NULL,
  `naziv` varchar(255) NOT NULL,
  `opis` text NOT NULL,
  `cijena` float NOT NULL,
  `popust` float NOT NULL,
  `prodavac` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `proizvodi`
--

INSERT INTO `proizvodi` (`idPro`, `naziv`, `opis`, `cijena`, `popust`, `prodavac`) VALUES
(1, 'djole', 'izvacemogadjole', 69, 50, 1),
(9, 'djole1', 'izvacemogadjole', 0, 2, 1),
(10, 'djoleL', 'izvacemogadjole', 2, 0, 1),
(11, 'lopta', 'loptasta_lopta', 2, 50, 2),
(12, 'bobe', 'bobe_bombaste', 10, 10, 4);

-- --------------------------------------------------------

--
-- Table structure for table `proizvodi_u_korpi`
--

CREATE TABLE `proizvodi_u_korpi` (
  `idPUK` int(11) NOT NULL,
  `id_korpe` int(11) NOT NULL,
  `id_proizvoda` int(11) NOT NULL,
  `kolicina` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `proizvodi_u_korpi`
--

INSERT INTO `proizvodi_u_korpi` (`idPUK`, `id_korpe`, `id_proizvoda`, `kolicina`) VALUES
(1, 1, 1, 10),
(2, 2, 10, 0),
(3, 4, 11, 15),
(4, 5, 10, 0),
(5, 5, 11, 0),
(6, 6, 12, 0),
(7, 6, 10, 0);

-- --------------------------------------------------------

--
-- Table structure for table `recenzije`
--

CREATE TABLE `recenzije` (
  `idRec` int(11) NOT NULL,
  `id_proizvoda` int(11) NOT NULL,
  `ocjena` int(11) NOT NULL,
  `opis` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `stavke`
--

CREATE TABLE `stavke` (
  `idSta` int(11) NOT NULL,
  `idNarudzbine` int(11) DEFAULT NULL,
  `idProizvoda` int(11) DEFAULT NULL,
  `kolicina` int(11) DEFAULT NULL,
  `cijenaPoKomadu` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `stavke`
--

INSERT INTO `stavke` (`idSta`, `idNarudzbine`, `idProizvoda`, `kolicina`, `cijenaPoKomadu`) VALUES
(1, 2, 11, 15, 1),
(2, 3, 12, 10, 9),
(3, 3, 10, 5, 2);

-- --------------------------------------------------------

--
-- Table structure for table `transakcije`
--

CREATE TABLE `transakcije` (
  `id` int(11) NOT NULL,
  `id_kupca` int(11) NOT NULL,
  `iznos` float NOT NULL,
  `id_narudzbine` int(11) NOT NULL,
  `vrijeme_placanja` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transakcije`
--

INSERT INTO `transakcije` (`id`, `id_kupca`, `iznos`, `id_narudzbine`, `vrijeme_placanja`) VALUES
(1, 5, 0, 1, '2023-02-21 23:17:53'),
(2, 5, 15, 2, '2023-02-21 23:29:39'),
(3, 6, 100, 3, '2023-02-22 01:10:23');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `korisnici`
--
ALTER TABLE `korisnici`
  ADD PRIMARY KEY (`idKor`);

--
-- Indexes for table `korpe`
--
ALTER TABLE `korpe`
  ADD PRIMARY KEY (`idKorp`),
  ADD KEY `id_korisnika` (`id_korisnika`);

--
-- Indexes for table `narudzbine`
--
ALTER TABLE `narudzbine`
  ADD PRIMARY KEY (`idNar`);

--
-- Indexes for table `proizvodi`
--
ALTER TABLE `proizvodi`
  ADD PRIMARY KEY (`idPro`),
  ADD KEY `prodavac` (`prodavac`);

--
-- Indexes for table `proizvodi_u_korpi`
--
ALTER TABLE `proizvodi_u_korpi`
  ADD PRIMARY KEY (`idPUK`),
  ADD KEY `id_korpe` (`id_korpe`),
  ADD KEY `id_proizvoda` (`id_proizvoda`);

--
-- Indexes for table `recenzije`
--
ALTER TABLE `recenzije`
  ADD PRIMARY KEY (`idRec`),
  ADD KEY `id_proizvoda` (`id_proizvoda`);

--
-- Indexes for table `stavke`
--
ALTER TABLE `stavke`
  ADD PRIMARY KEY (`idSta`),
  ADD KEY `idNarudzbine` (`idNarudzbine`),
  ADD KEY `idProizvoda` (`idProizvoda`);

--
-- Indexes for table `transakcije`
--
ALTER TABLE `transakcije`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_kupca` (`id_kupca`),
  ADD KEY `id_narudzbine` (`id_narudzbine`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `korisnici`
--
ALTER TABLE `korisnici`
  MODIFY `idKor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `korpe`
--
ALTER TABLE `korpe`
  MODIFY `idKorp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `narudzbine`
--
ALTER TABLE `narudzbine`
  MODIFY `idNar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `proizvodi`
--
ALTER TABLE `proizvodi`
  MODIFY `idPro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `proizvodi_u_korpi`
--
ALTER TABLE `proizvodi_u_korpi`
  MODIFY `idPUK` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `recenzije`
--
ALTER TABLE `recenzije`
  MODIFY `idRec` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `stavke`
--
ALTER TABLE `stavke`
  MODIFY `idSta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `transakcije`
--
ALTER TABLE `transakcije`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `korpe`
--
ALTER TABLE `korpe`
  ADD CONSTRAINT `korpe_ibfk_1` FOREIGN KEY (`id_korisnika`) REFERENCES `korisnici` (`idKor`);

--
-- Constraints for table `proizvodi`
--
ALTER TABLE `proizvodi`
  ADD CONSTRAINT `proizvodi_ibfk_1` FOREIGN KEY (`prodavac`) REFERENCES `korisnici` (`idKor`);

--
-- Constraints for table `proizvodi_u_korpi`
--
ALTER TABLE `proizvodi_u_korpi`
  ADD CONSTRAINT `proizvodi_u_korpi_ibfk_1` FOREIGN KEY (`id_korpe`) REFERENCES `korpe` (`idKorp`),
  ADD CONSTRAINT `proizvodi_u_korpi_ibfk_2` FOREIGN KEY (`id_proizvoda`) REFERENCES `proizvodi` (`idPro`);

--
-- Constraints for table `recenzije`
--
ALTER TABLE `recenzije`
  ADD CONSTRAINT `recenzije_ibfk_1` FOREIGN KEY (`id_proizvoda`) REFERENCES `proizvodi` (`idPro`);

--
-- Constraints for table `stavke`
--
ALTER TABLE `stavke`
  ADD CONSTRAINT `stavke_ibfk_1` FOREIGN KEY (`idNarudzbine`) REFERENCES `narudzbine` (`idNar`),
  ADD CONSTRAINT `stavke_ibfk_2` FOREIGN KEY (`idProizvoda`) REFERENCES `proizvodi` (`idPro`);

--
-- Constraints for table `transakcije`
--
ALTER TABLE `transakcije`
  ADD CONSTRAINT `transakcije_ibfk_1` FOREIGN KEY (`id_kupca`) REFERENCES `korisnici` (`idKor`),
  ADD CONSTRAINT `transakcije_ibfk_2` FOREIGN KEY (`id_narudzbine`) REFERENCES `narudzbine` (`idNar`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
