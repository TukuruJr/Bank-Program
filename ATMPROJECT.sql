-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jul 30, 2022 at 12:11 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ATMPROJECT`
--

-- --------------------------------------------------------

--
-- Table structure for table `Admin`
--

CREATE TABLE `Admin` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Admin`
--

INSERT INTO `Admin` (`id`, `username`, `password`) VALUES
(1, 'saf', 'pass');

-- --------------------------------------------------------

--
-- Table structure for table `Agents`
--

CREATE TABLE `Agents` (
  `id` int(11) NOT NULL,
  `b_name` varchar(50) NOT NULL,
  `location` varchar(50) NOT NULL,
  `o_name` varchar(50) NOT NULL,
  `o_id` int(11) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Agents`
--

INSERT INTO `Agents` (`id`, `b_name`, `location`, `o_name`, `o_id`, `password`) VALUES
(3, 'business', 'gatwe', 'kamaa', 2, 'pass'),
(5, 'comps', 'gatwe', 'munge', 12345, 'xx');

-- --------------------------------------------------------

--
-- Table structure for table `statements`
--

CREATE TABLE `statements` (
  `uid` int(11) NOT NULL,
  `account` int(100) NOT NULL,
  `date` varchar(20) NOT NULL,
  `type` text NOT NULL,
  `amount` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `statements`
--

INSERT INTO `statements` (`uid`, `account`, `date`, `type`, `amount`) VALUES
(7, 11223344, '2022-07-29', 'Transfer to 1122334', 100),
(8, 1122334, '2022-07-29', 'Recieved from 11223344', 100),
(9, 1122334, '2022-07-29', 'DEPOSIT ', 67);

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE `Users` (
  `fullname` varchar(100) NOT NULL,
  `idno` int(20) NOT NULL,
  `account` int(20) NOT NULL,
  `balance` double NOT NULL,
  `pin` int(4) NOT NULL,
  `mobile` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Users`
--

INSERT INTO `Users` (`fullname`, `idno`, `account`, `balance`, `pin`, `mobile`) VALUES
('kenda', 4, 1, 140, 11, '234'),
('meme', 12, 11223344, 100, 2345, '334455'),
('francis', 123, 1122334, 172, 2345, '334455');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Admin`
--
ALTER TABLE `Admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Agents`
--
ALTER TABLE `Agents`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `statements`
--
ALTER TABLE `statements`
  ADD PRIMARY KEY (`uid`);

--
-- Indexes for table `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`idno`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Admin`
--
ALTER TABLE `Admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `Agents`
--
ALTER TABLE `Agents`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `statements`
--
ALTER TABLE `statements`
  MODIFY `uid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
