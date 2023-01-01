-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 21, 2022 at 05:26 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 7.4.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `qlnv`
--

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `DEPT_ID` int(11) NOT NULL,
  `DEPT_NAME` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DEPT_NO` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `LOCATION` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`DEPT_ID`, `DEPT_NAME`, `DEPT_NO`, `LOCATION`) VALUES
(1, 'IT', '01', 'Ha Noi'),
(2, 'HR', '02', 'Ha Noi'),
(3, 'HR', '03', 'Ha Noi');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `EMP_ID` bigint(20) NOT NULL,
  `EMP_NAME` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `EMP_NO` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `HIRE_DATE` date NOT NULL,
  `IMAGE` longblob DEFAULT NULL,
  `JOB` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `SALARY` float NOT NULL,
  `DEPT_ID` int(11) NOT NULL,
  `MNG_ID` bigint(20) DEFAULT NULL,
  `GRD_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`EMP_ID`, `EMP_NAME`, `EMP_NO`, `HIRE_DATE`, `IMAGE`, `JOB`, `SALARY`, `DEPT_ID`, `MNG_ID`, `GRD_ID`) VALUES
(1, 'Nguyen', '001', '2022-10-21', '', 'IT', 1000, 1, NULL, NULL),
(2, 'Linh', '002', '2022-10-21', '', 'IT', 1000, 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `salary_grade`
--

CREATE TABLE `salary_grade` (
  `GRADE` int(11) NOT NULL,
  `HIGH_SALARY` float NOT NULL,
  `LOW_SALARY` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `salary_grade`
--

INSERT INTO `salary_grade` (`GRADE`, `HIGH_SALARY`, `LOW_SALARY`) VALUES
(1, 1000, 500),
(2, 2000, 1000);

-- --------------------------------------------------------

--
-- Table structure for table `timekeeper`
--

CREATE TABLE `timekeeper` (
  `Timekeeper_Id` bigint(20) NOT NULL,
  `Date_Time` datetime NOT NULL,
  `In_Out` char(1) COLLATE utf8mb4_unicode_ci NOT NULL,
  `EMP_ID` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `timekeeper`
--

INSERT INTO `timekeeper` (`Timekeeper_Id`, `Date_Time`, `In_Out`, `EMP_ID`) VALUES
(1, '2022-10-21 20:43:13', '1', 1),
(2, '2022-10-21 20:43:28', '0', 1),
(3, '2022-10-21 00:00:00', '1', 1),
(4, '2022-10-19 00:00:00', '1', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`DEPT_ID`),
  ADD UNIQUE KEY `DEPT_NO` (`DEPT_NO`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`EMP_ID`),
  ADD UNIQUE KEY `EMP_NO` (`EMP_NO`),
  ADD KEY `employee_DEPT_ID_fkey` (`DEPT_ID`),
  ADD KEY `employee_GRD_ID_fkey` (`GRD_ID`);

--
-- Indexes for table `salary_grade`
--
ALTER TABLE `salary_grade`
  ADD PRIMARY KEY (`GRADE`);

--
-- Indexes for table `timekeeper`
--
ALTER TABLE `timekeeper`
  ADD PRIMARY KEY (`Timekeeper_Id`),
  ADD KEY `FK744D9BFF6106A42` (`EMP_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
  MODIFY `DEPT_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `EMP_ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `salary_grade`
--
ALTER TABLE `salary_grade`
  MODIFY `GRADE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `timekeeper`
--
ALTER TABLE `timekeeper`
  MODIFY `Timekeeper_Id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `employee_DEPT_ID_fkey` FOREIGN KEY (`DEPT_ID`) REFERENCES `department` (`DEPT_ID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `employee_GRD_ID_fkey` FOREIGN KEY (`GRD_ID`) REFERENCES `salary_grade` (`GRADE`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `timekeeper`
--
ALTER TABLE `timekeeper`
  ADD CONSTRAINT `FK744D9BFF6106A42` FOREIGN KEY (`EMP_ID`) REFERENCES `employee` (`EMP_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
