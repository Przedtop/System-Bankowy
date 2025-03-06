-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 06, 2025 at 06:45 PM
-- Wersja serwera: 10.4.32-MariaDB
-- Wersja PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `systembankowy`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `accounts`
--

CREATE TABLE `accounts` (
  `id` int(11) NOT NULL,
  `account_number` int(11) NOT NULL,
  `balance` double NOT NULL,
  `create_date` text NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`id`, `account_number`, `balance`, `create_date`, `user_id`) VALUES
(2, 9341124, 2123, 'accountRequestDataModel.getData_zalozenia()', 5),
(4, 1241, 8000, 'asfd', 7),
(5, 1241, 8000, 'asfd', 7),
(6, 100, 7500, 'asfd', 7),
(7, 101, 8500, 'asfd', 7),
(8, 681009124, 0, '1000:10:2025', 12),
(10, 10, 400, '100-100', 14),
(11, 10, 400, '100-100', 14),
(12, 10, 400, '100-100', 14),
(13, 10, 400, '100-100', 14);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `lastName` varchar(31) NOT NULL,
  `identificationNumber` int(11) NOT NULL,
  `login` varchar(24) NOT NULL,
  `password` varchar(24) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `lastName`, `identificationNumber`, `login`, `password`) VALUES
(1, 'dasd', 'asd', 1234, 'skibi', 'Asss'),
(3, 'Imie', 'Nazwis', 123124, 'Login', 'Haslo'),
(4, 'Imie', 'Nazwis', 123124, 'Login', 'Haslo'),
(5, 'Jan', 'Kowalski', 123444, 'janek', 'password123'),
(6, 'dasd', 'asdasd', 123, 'asdasd', 'aaaaaaaaaa'),
(7, 'asd', 'asdqa', 1190123, 'login', 'haslo'),
(8, 'asd', 'asdqa', 1190123, 'login', 'haslo1'),
(9, 'Jan', 'Kowalski', 0, 'janek', 'password123'),
(10, 'Jan', 'Kowalski', 9090123, 'janek', 'password123'),
(11, 'Jan', 'Kowalski', 9090123, 'janek', 'password123'),
(12, 'Jan', 'Kowalski', 0, 'janek', 'password123'),
(13, 'Jan', 'Kowalski', 0, 'janek', 'password123'),
(14, 'Jan', 'Kowalski', 123444, 'janek', 'password123'),
(15, 'Jan', 'Kowalski', 123444, 'janek', 'password123'),
(16, 'Jan', 'Kowalski', 123444, 'janek', 'password123'),
(17, 'Jan', 'Kowalski', 0, 'janek', 'mojehaslo'),
(18, 'Jan', 'Kowalski', 99, 'janek', 'mojehaslo'),
(19, 'skibid', 'asfd', 100000, 'logjmn', 'asdhaslo'),
(20, 'lebisko', 'leb', 12351, 'asd', 'haselisko');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accounts`
--
ALTER TABLE `accounts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
