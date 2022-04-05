-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 01. Apr 2022 um 12:51
-- Server-Version: 10.4.22-MariaDB
-- PHP-Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


DROP TABLE IF EXISTS data;

--
-- Datenbank: `java3_qdata`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `data`
--

CREATE TABLE IF NOT EXISTS `data`(
  `id` bigint(20) NOT NULL,
  `arbeitsplatz` int(11) NOT NULL,
  `art` varchar(255) DEFAULT NULL,
  `baugruppe` varchar(255) DEFAULT NULL,
  `baumuster` int(11) NOT NULL,
  `beschreibung` varchar(255) DEFAULT NULL,
  `cgrprobl` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `internal_workplace` varchar(255) DEFAULT NULL,
  `massnahmen_code` int(11) NOT NULL,
  `massnahmen_text` varchar(255) DEFAULT NULL,
  `mat` varchar(255) DEFAULT NULL,
  `mat_kurz_text` varchar(255) DEFAULT NULL,
  `meldung` bigint(20) NOT NULL,
  `modify_date` datetime DEFAULT NULL,
  `ortnumber` int(11) NOT NULL,
  `position_number` int(11) NOT NULL,
  `position_text` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `pruefling` varchar(255) DEFAULT NULL,
  `ref_number` varchar(255) DEFAULT NULL,
  `scd` varchar(255) DEFAULT NULL,
  `stoer_beginn` date DEFAULT NULL,
  `task_closed` date DEFAULT NULL,
  `task_closed_by` varchar(255) DEFAULT NULL,
  `ursache` int(11) NOT NULL,
  `ursachen_nummer` varchar(255) DEFAULT NULL,
  `verantwortlicher` int(11) NOT NULL,
  `werksnummer` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `data`
--

INSERT INTO `data` (`id`, `arbeitsplatz`, `art`, `baugruppe`, `baumuster`, `beschreibung`, `cgrprobl`, `create_date`, `created_by`, `internal_workplace`, `massnahmen_code`, `massnahmen_text`, `mat`, `mat_kurz_text`, `meldung`, `modify_date`, `ortnumber`, `position_number`, `position_text`, `price`, `pruefling`, `ref_number`, `scd`, `stoer_beginn`, `task_closed`, `task_closed_by`, `ursache`, `ursachen_nummer`, `verantwortlicher`, `werksnummer`) VALUES
(1, 0, 'B2', 'D5348008223300', 56, 'Neuteile Erforderlich', 'ZRU-BO', '2022-04-01 10:37:07', '1234', '20150', 1001, '', 'D5347418002200', 'Montage C70', 400092266, '2022-04-01 10:37:07', 0, 0, '.', 0, '1994478', '', 'BO02', '2021-04-20', '2021-04-20', '1234', 1, 'C34', 50000711, 1800),
(2, 0, 'B2', 'D5347210120000', 56, 'Neuteile Erforderlich', 'ZRU-BO', '2022-04-01 10:37:07', '1234', '20150', 1001, '', 'D5347418002200', 'Montage C70', 400092745, '2022-04-01 10:37:07', 0, 0, '.', 0, '1993387', '', 'BO02', '2021-05-19', '2021-05-19', '1234', 1, 'C34', 50000711, 1800),
(3, 0, 'B2', 'D5347210420200', 56, 'Neuteile Erforderlich', 'ZRU-BO', '2022-04-01 10:37:07', '1234', '20150', 1001, '', 'D5347418002000', 'Montage C70', 400092064, '2022-04-01 10:37:07', 0, 0, '.', 20.41, '1993387', '', 'BO03', '2021-04-07', '2021-04-07', '1234', 2, 'C34', 50000711, 1800),
(4, 0, 'B2', 'D5348008231800', 56, 'Neuteile Erforderlich', 'ZRU-BE', '2022-04-01 10:37:07', '1234', '20150', 1001, '', 'D5347418002200', 'Montage C70', 400092745, '2022-04-01 10:37:07', 0, 0, '.', 0, '1997360', '', 'BE04', '2021-05-19', '2021-05-19', '1234', 1, 'C34', 50000711, 1800),
(5, 0, 'B2', 'D5347210421600', 56, 'Neuteile Erforderlich', 'ZRU-BO', '2022-04-01 10:37:07', '1234', '20150', 1001, '', 'D5347418002200', 'Montage C70', 400091531, '2022-04-01 10:37:07', 0, 0, '.', 29.28, '2004968', '', 'BO05', '2021-03-09', '2021-03-09', '1234', 1, 'C34', 50000711, 1800),
(6, 0, 'B2', 'D5347123320000', 56, 'Neuteile Erforderlich', 'ZRU-BO', '2022-04-01 10:37:07', '1234', '20150', 1001, '', 'D5347418002200', 'Montage C70', 400091531, '2022-04-01 10:37:07', 0, 0, '.', 18.5, '2004907', '', 'BO01', '2021-03-09', '2021-03-09', '1234', 1, 'C34', 50000711, 1800),
(7, 0, 'B2', 'D5347210420200', 56, 'Bauteil Beschädigt', 'ZRU-BE', '2022-04-01 10:37:07', '1234', '20150', 1001, '', 'D5347418002200', 'Montage C70', 400091765, '2022-04-01 10:37:07', 0, 0, '.', 20.41, '2004935', '', 'BE04', '2021-03-22', '2021-03-22', '1234', 1, 'C34', 50000711, 1800),
(8, 0, 'B4', 'D5347210420200', 56, 'Halter Pos.14 an Y-464.8', 'ZRU-MA', '2022-04-01 10:37:07', '1234', '20150', 1001, '', 'D5347418002200', 'Montage C70', 700071530, '2022-04-01 10:37:07', 0, 0, 'Kundenbeanstandung', 0, '1111111', '14828781', 'MA08', '2021-03-01', '2021-03-15', '1234', 5, 'C34', 50001876, 1800),
(9, 0, 'B2', 'D5347266302800', 56, 'Neuteile Erforderlich', 'ZRU-BO', '2022-04-01 10:37:07', '1234', '20150', 1001, '', 'D5347418002000', 'Montage C70', 400092064, '2022-04-01 10:37:07', 0, 0, '.', 0, 'KIT AUGSBURG', '', 'BO02', '2021-04-07', '2021-04-07', '1234', 2, 'C34', 50000711, 1800),
(10, 0, 'B2', 'D5347123320000', 56, 'Neuteile Erforderlich', 'ZRU-BO', '2022-04-01 10:37:07', '1234', '20150', 1001, '', 'D5347418002200', 'Montage C70', 400091531, '2022-04-01 10:37:07', 0, 0, '.', 0, '2004907', '', 'BO01', '2021-03-09', '2021-03-09', '1234', 1, 'C34', 50000711, 1800);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `data`
--
ALTER TABLE `data`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `data`
--
ALTER TABLE `data`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;
