-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 12, 2025 at 03:11 PM
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
(0, 0, 0, '', 0),
(2, 9341124, 2123, 'accountRequestDataModel.getData_zalozenia()', 5),
(4, 1241, 8000, 'asfd', 7),
(5, 1241, 8000, 'asfd', 7),
(6, 100, 7595.8, 'asfd', 7),
(7, 101, 9105.000000000004, 'asfd', 7),
(8, 681009124, 0, '1000:10:2025', 12),
(10, 10, 100, '100-100', 14),
(14, 223816382, 1000, '2025-03-07 17:56', 0),
(15, 824687255, 1000, '2025-03-12 13:05', 0);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `last_name` varchar(31) NOT NULL,
  `identification_number` int(11) NOT NULL,
  `login` varchar(24) NOT NULL,
  `password` varchar(24) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `last_name`, `identification_number`, `login`, `password`) VALUES
(1, '123tal', 'nie', 123451, 'klgao', 'juasd'),
(3, 'Imie', 'Nazwis', 123124, 'Login', 'Haslo'),
(4, 'Imie', 'Nazwis', 123124, 'Login', 'Haslo'),
(5, 'Jan', 'Kowalski', 123444, 'janek', 'password123'),
(6, 'dasd', 'asdasd', 123, 'asdasd', 'aaaaaaaaaa'),
(7, 'asd', 'asdqa', 1190123, 'login', 'haslo'),
(8, 'asd', 'asdqa', 1190123, 'login', 'haslo1'),
(9, 'Jan', 'Kowalski', 0, 'janek', 'password123'),
(10, 'azsd', 'asd1', 12301, 'niema123mloginu', 'skibi123di'),
(11, 'Jan', 'Kowalski', 9090123, 'janek', 'password123'),
(14, 'Jan', 'Kowalski', 123444, 'janek', 'password123'),
(15, 'Jan', 'Kowalski', 123444, 'janek', 'password123'),
(16, 'Jan', 'Kowalski', 123444, 'janek', 'password123'),
(17, 'Jan', 'Kowalski', 0, 'janek', 'mojehaslo'),
(18, 'Jan', 'Kowalski', 99, 'janek', 'mojehaslo'),
(19, '123tal', 'nie', 123451, 'klgao', 'juasd'),
(23, '123tal', 'nie', 123451, 'klgao', 'juasd'),
(24, '123tal', 'nie', 123451, 'klgao', 'juasd'),
(25, '123tal', 'nie', 123451, 'klgao', 'juasd'),
(26, '123tal', 'nie', 123451, 'klgao', 'juasd'),
(27, '123', '123', 123, '123', '123'),
(28, '123asd', '123', 123, '123', '123'),
(29, '123asd', '123', 123, '123', '123'),
(30, '', '123', 123, '123', '123'),
(31, '', '12331', 121233, '123123', '121233'),
(32, 'siema asd', '12331', 121233, '123123', '121233'),
(33, '', '12331', 121233, '123123', '121233'),
(34, '', '123', 121233, '123123', '121233'),
(35, '', '123', 123, '123123', '121233'),
(36, '', '123', 12, '123123', '121233'),
(37, 'azsd', 'asd1', 1, 'niemamloginu', 'skibidi'),
(38, 'azsd', 'asd1', 1, 'niemamloginu', 'skibidi'),
(39, 'azsd', 'asd1', 1, 'niemamloginu', 'skibidi'),
(40, 'azsd', 'asd1', 1, 'niemamloginu', 'skibidi'),
(41, 'azsd', 'asd1', 1, 'niemamloginu', 'skibidi'),
(42, 'azsd', 'asd1', 1, 'niemamloginu', 'skibidi'),
(43, 'azsd', 'asd1', 1, 'niemamloginu', 'skibidi'),
(44, 'azsd', 'asd1', 12301, 'niema123mloginu', 'skibi123di');

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
