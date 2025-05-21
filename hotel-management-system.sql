-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 21, 2025 at 09:45 PM
-- Server version: 8.0.39
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotel-management-system`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `booking_id` int NOT NULL,
  `check_in_date` date NOT NULL,
  `check_out_date` date NOT NULL,
  `no_of_guest` int NOT NULL,
  `total_amount` float NOT NULL,
  `status` varchar(25) NOT NULL,
  `user_id` int NOT NULL,
  `room_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`booking_id`, `check_in_date`, `check_out_date`, `no_of_guest`, `total_amount`, `status`, `user_id`, `room_id`) VALUES
(18, '2025-05-21', '2025-05-23', 2, 1600, 'Checked Out', 15, 1),
(19, '2025-05-21', '2025-05-24', 4, 3600, 'Checked Out', 15, 5),
(20, '2025-05-25', '2025-05-27', 4, 2400, 'Checked Out', 12, 5),
(21, '2025-05-29', '2025-05-31', 5, 3000, 'Booked', 12, 7);

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `menu_id` int NOT NULL,
  `food_name` varchar(30) NOT NULL,
  `category` varchar(20) NOT NULL,
  `food_description` varchar(70) NOT NULL,
  `menu_price` float NOT NULL,
  `menu_photo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`menu_id`, `food_name`, `category`, `food_description`, `menu_price`, `menu_photo`) VALUES
(7, 'Thakali Khana', 'Nepali Khana', 'Authentic Thakali Flavour', 500, 'Thakali Plate.jpeg'),
(10, 'Nepali Khaja Set', 'Nepali Khaja', 'Best Khaja Set', 350, 'Nepali Khaja Set.jpg'),
(11, 'Momo', 'Nepali Khaja', 'Pure Nepali style Momo', 140, 'Momo.jpeg'),
(12, 'Newari Khaja set', 'Nepali Khaja', 'Typical Newari Khaja Set', 250, 'Newari.jpeg');

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `room_id` int NOT NULL,
  `room_no` int NOT NULL,
  `room_type` varchar(40) NOT NULL,
  `room_description` varchar(150) NOT NULL,
  `price_per_day` float NOT NULL,
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `room_photo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`room_id`, `room_no`, `room_type`, `room_description`, `price_per_day`, `status`, `room_photo`) VALUES
(1, 101, 'Single', 'Good for single person', 800, 'Available', 'single_2.jpeg'),
(5, 202, 'Double', 'Best for Double person', 1200, 'Available', 'Double_2.jpeg'),
(7, 301, 'Triple', 'Best for family', 1500, 'Not Available', 'Triple_3.jpeg'),
(8, 203, 'Triple', 'Best for family visit ', 1600, 'available', 'Triple_5.jpeg'),
(9, 405, 'Cottage', 'For family function', 2000, 'available', 'Cottage_ 1.jpeg'),
(10, 408, 'Cottage', 'For birthday celebration', 2100, 'available', 'Cottage_ 4.jpeg'),
(11, 106, 'Single', 'For solo travellers', 900, 'available', 'single_3.jpeg');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int NOT NULL,
  `full_name` varchar(30) NOT NULL,
  `email` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone_number` varchar(40) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(10) NOT NULL,
  `profile_photo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `full_name`, `email`, `phone_number`, `gender`, `password`, `role`, `profile_photo`) VALUES
(6, 'Main Admin', 'admin@gmail.com', '9823532346', 'male', 'uZQEhWjh8NcViDB2SxXIj5kNSgTF9MB36CdjHpezySSlhb1rNtYB4msM9MnIXNHGQG7DcJU=', 'ADMIN', ''),
(12, 'Binod Sarki', 'binod@gmail.com', '9876234321', 'male', 'ueBEdjHkeL2bnk45WCRxQjgLQsPtzIyiHtfZd0aZeZub4632HP4KroCvFLnzNpldsk/4LS8=', 'USER', 'binod.jpg'),
(13, 'Md Inja', 'md@gmail.com', '9813532387', 'male', '2Pkxj3d/sS5MBVpkAoLaErUhm62KbFLSSlm8uCvzpawoYIe92Fn1r0AGh0uv98F402uokQ==', 'USER', 'injamamul.png'),
(14, 'Hari Chandra', 'hari@gmail.com', '9812348763', 'male', '4HMolTN3NrfFhwnOkykcYaouduFOnF1ppwsH9UQ/CJBLwkS16ujP49yaZFWiaQcQ9I7dBw==', 'USER', 'hari.png'),
(15, 'Abhi Shah', 'abhi@gmail.com', '9823422342', 'male', 'NCzaBIYDapNXoqU+H/4K+OkeA65yO+oTHD7u4MY7pLBkLq1J371yBJqWTL4blZA1q6PLyQ==', 'USER', 'abhinav.png'),
(16, 'Nischal Tharu', 'nischal@gmail.com', '9812345678', 'male', 'U6nD3ZcAVKmt5InljmsmaoDf2NLvRGrScQQo0TXcdAFjmnwyfsWj1YC5PZZpS/CmgUNyL8e1aw==', 'USER', 'nischal.png');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`booking_id`),
  ADD KEY `user_fk` (`user_id`) USING BTREE,
  ADD KEY `room_fk` (`room_id`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`menu_id`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`room_id`),
  ADD UNIQUE KEY `room_no` (`room_no`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `phone_number` (`phone_number`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `booking_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `menu`
--
ALTER TABLE `menu`
  MODIFY `menu_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `room`
--
ALTER TABLE `room`
  MODIFY `room_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `booking`
--
ALTER TABLE `booking`
  ADD CONSTRAINT `room_fk` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
