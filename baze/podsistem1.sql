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
-- Database: `podsistem1`
--

-- --------------------------------------------------------

--
-- Table structure for table `grad`
--

CREATE TABLE `grad` (
  `id` int(11) NOT NULL,
  `naziv` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `grad`
--

INSERT INTO `grad` (`id`, `naziv`) VALUES
(1, 'vanjogras'),
(2, 'vaaaaaaaaaaa'),
(3, 'vanjoaaaaa'),
(4, 'vbb'),
(5, 'vbbb'),
(6, 'gg'),
(7, 'vvv'),
(8, 'vanjoslav'),
(9, 'fggfsdgdfg'),
(10, 'vanjos'),
(11, 'grgr'),
(12, 'vanja'),
(13, 'grgr1'),
(14, 'grgr12'),
(15, 'vanja2'),
(16, 'vanja22'),
(17, 'vanjoza'),
(18, 'GRAD'),
(19, 'denijolgrad'),
(20, 'vanjozaaaa'),
(21, 'bubigrad'),
(22, 'kojigradvise');

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

CREATE TABLE `korisnik` (
  `id` int(11) NOT NULL,
  `ime_prezima` varchar(25) NOT NULL,
  `korisnicko_ime` varchar(25) NOT NULL,
  `lozinka` varchar(25) NOT NULL,
  `adresa` varchar(25) NOT NULL,
  `grad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`id`, `ime_prezima`, `korisnicko_ime`, `lozinka`, `adresa`, `grad`) VALUES
(1, 'vanjoza', 'vanjoza', 'vanjoza', 'adresa', 11),
(2, 'nikola', 'jona', 'jona', 'denislav', 6),
(4, 'grgr', 'jasam', '123', 'aaaa', 1),
(5, 'lestra', 'lestra', 'strale', '1', 4),
(6, 'cmiki', 'cmiki', 'cmiki', 'bulkravljaaleks43', 7);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `grad`
--
ALTER TABLE `grad`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD PRIMARY KEY (`id`),
  ADD KEY `grad` (`grad`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `grad`
--
ALTER TABLE `grad`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `korisnik`
--
ALTER TABLE `korisnik`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD CONSTRAINT `grad` FOREIGN KEY (`grad`) REFERENCES `grad` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
