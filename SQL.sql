-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-10-2016 a las 15:47:34
-- Versión del servidor: 5.7.9
-- Versión de PHP: 5.6.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyecto`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `autor`
--

DROP TABLE IF EXISTS `autor`;
CREATE TABLE IF NOT EXISTS `autor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `autor`
--

INSERT INTO `autor` (`id`, `nombre`) VALUES
(1, 'J.R.R. Tolkien'),
(2, 'Ezequiel García'),
(3, 'Claudia Hoffner'),
(4, 'Laura Cabezas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `basesdatos`
--

DROP TABLE IF EXISTS `basesdatos`;
CREATE TABLE IF NOT EXISTS `basesdatos` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `basesdatos`
--

INSERT INTO `basesdatos` (`id`, `nombre`) VALUES
(1, 'CRIJA'),
(2, 'CIJUL'),
(3, 'MASTERLEX'),
(4, 'OPAC'),
(5, 'SCIJ'),
(6, 'DIALNET'),
(7, 'OTRAS');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `consulta`
--

DROP TABLE IF EXISTS `consulta`;
CREATE TABLE IF NOT EXISTS `consulta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tema` varchar(100) DEFAULT NULL,
  `fechaEmision` timestamp NULL DEFAULT NULL,
  `fechaEntrega` timestamp NULL DEFAULT NULL,
  `observaciones` varchar(500) DEFAULT NULL,
  `usuario` varchar(9) DEFAULT NULL,
  `tipo` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_consulta_usuario_idx` (`usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `consulta`
--

INSERT INTO `consulta` (`id`, `tema`, `fechaEmision`, `fechaEntrega`, `observaciones`, `usuario`, `tipo`) VALUES
(4, 'Modificationator', '2016-07-03 22:49:00', '2016-08-26 09:21:00', 'Test modified', '765754365', 'Presencial'),
(5, 'Test', '2016-07-03 22:56:00', NULL, 'Test', '546853467', 'Presencial'),
(6, 'Test', '2016-07-03 23:05:00', NULL, 'Test', '453456745', 'Presencial'),
(7, 'Tesis', '2016-07-03 23:08:00', '2016-07-03 21:43:00', 'Ninguna', '676545678', 'Presencial'),
(8, 'Victoria super modificada', '2016-07-03 23:17:00', NULL, 'Demo para el profe', '456345678', 'Presencial'),
(9, 'Final test', '2016-07-03 23:20:00', '2016-08-30 08:42:00', 'Final', '656754567', 'Presencial'),
(10, 'Razones de fuerza mayor', '2016-07-05 00:11:00', '2016-07-04 20:54:00', 'No todo es observable', '405323345', 'Telefonica'),
(11, 'Casi lo logra', '2016-07-05 00:13:00', '2016-08-30 20:20:00', 'Nada util', '994535432', 'Telefonica'),
(12, 'Intento de rellenar este campo', '2016-07-05 00:14:00', NULL, 'Ninguna', '545693245', 'Presencial'),
(13, 'Prueba para el profe', '2016-07-06 19:23:00', NULL, 'Ninguna', '685645676', 'Telefonica'),
(14, 'Prueba', '2016-07-07 02:46:00', NULL, 'Ninguna', '765435645', 'Telefonica'),
(15, 'La locura', '2016-09-30 20:39:00', '2016-09-30 20:38:00', 'Ninguna', '234345676', 'Telefonica'),
(16, 'Trololololol', '2016-09-30 20:47:00', '2016-10-07 20:47:00', 'Nada', '123234546', 'Presencial'),
(17, 'Nada', '2016-09-30 21:04:00', '2016-10-07 21:04:00', 'Ninguna', '345456576', 'Telefonica'),
(18, 'Yo soy', '2016-09-30 21:10:00', NULL, 'Ninguna', '345456765', 'Telefonica'),
(19, 'Filosofía inglesa del siglo XVII', '2016-10-05 20:08:00', NULL, 'Ninguna', '567765789', 'Telefonica');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `consultabase`
--

DROP TABLE IF EXISTS `consultabase`;
CREATE TABLE IF NOT EXISTS `consultabase` (
  `basedatos` int(2) NOT NULL,
  `Consulta` int(6) DEFAULT NULL,
  KEY `fk_consultaBaseBDatos_idx` (`basedatos`),
  KEY `fk_consultaBase_consulta_idx` (`Consulta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `consultabase`
--

INSERT INTO `consultabase` (`basedatos`, `Consulta`) VALUES
(4, 7),
(5, 7),
(6, 7),
(1, 9),
(2, 9),
(4, 9),
(5, 10),
(6, 10),
(1, 11),
(3, 13),
(6, 13),
(4, 14),
(3, 6),
(4, 6),
(5, 8),
(6, 8),
(7, 4),
(7, 8),
(3, 15),
(2, 17);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `descriptor`
--

DROP TABLE IF EXISTS `descriptor`;
CREATE TABLE IF NOT EXISTS `descriptor` (
  `descriptor` varchar(200) NOT NULL,
  `signatura` varchar(20) NOT NULL,
  PRIMARY KEY (`descriptor`,`signatura`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `documento`
--

DROP TABLE IF EXISTS `documento`;
CREATE TABLE IF NOT EXISTS `documento` (
  `signatura` varchar(20) NOT NULL,
  `titulo` varchar(100) DEFAULT NULL,
  `volumen` int(3) DEFAULT NULL,
  `numero` int(5) DEFAULT NULL,
  `anio` char(5) DEFAULT NULL,
  `institucion` varchar(50) DEFAULT NULL,
  `editorial` varchar(60) DEFAULT NULL,
  `tipoDocumento` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`signatura`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `documento`
--

INSERT INTO `documento` (`signatura`, `titulo`, `volumen`, `numero`, `anio`, `institucion`, `editorial`, `tipoDocumento`) VALUES
('S123456', 'El Señor de los Anillos', NULL, NULL, NULL, NULL, 'Alcurnia', 'Libro'),
('S123457', 'The Silmarillion', NULL, NULL, NULL, NULL, NULL, 'Libro'),
('S453004', 'Mundo Abogado', 3, 45, '2007', NULL, NULL, 'Revista'),
('S956046', 'Derecho Consitucional en CR', NULL, NULL, '1999', 'UCR', NULL, 'Tesis');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `documentoautor`
--

DROP TABLE IF EXISTS `documentoautor`;
CREATE TABLE IF NOT EXISTS `documentoautor` (
  `documento` varchar(20) NOT NULL,
  `autor` int(6) DEFAULT NULL,
  KEY `fk_documentoAutor_autor_idx` (`autor`),
  KEY `fk_documentoAutor_documento_idx` (`documento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `documentoautor`
--

INSERT INTO `documentoautor` (`documento`, `autor`) VALUES
('S123456', 1),
('S123457', 1),
('S453004', 2),
('S956046', 3),
('S956046', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestamo`
--

DROP TABLE IF EXISTS `prestamo`;
CREATE TABLE IF NOT EXISTS `prestamo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `documento` varchar(20) DEFAULT NULL,
  `usuario` char(9) DEFAULT NULL,
  `estado` varchar(10) DEFAULT NULL,
  `fechaSolicitud` datetime DEFAULT NULL,
  `fechaEntrega` datetime DEFAULT NULL,
  `fechaCaducidad` datetime DEFAULT NULL,
  `fechaDevolucion` datetime DEFAULT NULL,
  `tipo` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_prestamoDocumento_idx` (`documento`),
  KEY `fk_prestamoUsuario_idx` (`usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `prestamo`
--

INSERT INTO `prestamo` (`id`, `documento`, `usuario`, `estado`, `fechaSolicitud`, `fechaEntrega`, `fechaCaducidad`, `fechaDevolucion`, `tipo`) VALUES
(2, 'S123456', '345234567', 'DEVUELTO', '2016-09-05 07:12:08', '2016-09-09 15:33:00', '2016-09-11 11:20:00', '2016-09-14 13:22:00', 'En Sala'),
(3, 'S453004', '676545678', 'MOROSO', '2016-09-07 16:16:00', '2016-09-14 13:37:00', '2016-09-24 13:00:00', NULL, 'En Sala'),
(4, 'S453004', '765754365', 'DENEGADO', '2016-09-01 09:43:30', NULL, NULL, NULL, NULL),
(5, 'S123456', '495643245', 'DEVUELTO', '2016-09-04 07:18:17', '2016-09-16 15:02:00', '2016-09-22 16:15:00', '2016-09-27 14:25:00', 'En Sala'),
(6, 'S956046', '657434567', 'DENEGADO', '2016-09-02 10:16:00', NULL, NULL, NULL, NULL),
(7, 'S453004', '123456789', 'MOROSO', '2016-09-14 08:32:17', '2016-09-27 14:23:00', '2016-09-27 14:24:00', NULL, 'En Sala'),
(8, 'S453004', '495643245', 'DEVUELTO', '2016-09-13 09:22:00', '2016-09-14 07:45:00', '2016-09-14 14:44:00', '2016-09-27 14:21:00', 'En Sala'),
(9, 'S956046', '994535432', 'MOROSO', '2016-09-14 09:27:00', '2016-09-17 13:17:00', '2016-09-20 13:05:00', NULL, 'En Sala'),
(10, 'S956046', '453456745', 'DEVUELTO', '2016-09-07 09:41:00', '2016-09-13 09:14:00', '2016-09-20 15:11:00', '2016-09-25 19:53:00', 'En Sala'),
(11, 'S453004', '395476844', NULL, '2016-09-27 13:32:00', NULL, NULL, NULL, NULL),
(12, 'S123457', '395476844', NULL, '2016-09-27 13:35:00', NULL, NULL, NULL, NULL),
(22, 'S123456', '395476844', NULL, '2016-09-27 14:14:00', NULL, NULL, NULL, NULL),
(23, 'S123456', '395476844', NULL, '2016-09-27 14:17:00', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `telefono`
--

DROP TABLE IF EXISTS `telefono`;
CREATE TABLE IF NOT EXISTS `telefono` (
  `cedula` char(9) NOT NULL,
  `numero` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`cedula`),
  UNIQUE KEY `cedula` (`cedula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `telefono`
--

INSERT INTO `telefono` (`cedula`, `numero`) VALUES
('123234546', '45456756'),
('123456789', '12345612'),
('234345676', '54678879'),
('342342342', '34563456'),
('345234567', '12345678'),
('345456576', '68540456'),
('345456765', '43456567'),
('395476844', '88643567'),
('405323345', '84345657'),
('453456745', '54678546'),
('456345678', '65434567'),
('495643245', '85345642'),
('495665892', '83054935'),
('545693245', '85434567'),
('546853467', '45687654'),
('567765789', '45605670'),
('596405675', '85450321'),
('656754567', '45034567'),
('657434567', '67543456'),
('676545678', '87645432'),
('685645676', '86545643'),
('765435645', '87645345'),
('765754365', '34523487'),
('994535432', '85432345');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `cedula` char(9) NOT NULL,
  `carne` varchar(9) DEFAULT NULL,
  `apellidos` varchar(100) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `institucion` varchar(40) DEFAULT NULL,
  `tipo` varchar(50) NOT NULL,
  `clave` varchar(20) DEFAULT NULL,
  `rol` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cedula`),
  UNIQUE KEY `cedula_UNIQUE` (`cedula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`cedula`, `carne`, `apellidos`, `nombre`, `email`, `institucion`, `tipo`, `clave`, `rol`) VALUES
('123234546', '', 'Menso Pessoa', 'Trololol', 'trol@trol.com', 'NoNAs', 'Asociado', NULL, NULL),
('123456789', '44567', 'Test Test', 'Test', 'test@test.com', 'Test', 'Funcionario', NULL, NULL),
('234345676', '', 'Locuaz Lolo', 'Loco', 'loc.lol@gmail.com', 'UCR', 'Funcionario', NULL, NULL),
('342342342', '43254', 'Test Test', 'Test', 'test@test.com', 'Test', 'Estudiante', NULL, NULL),
('345234567', '', 'Comosea Sisea', 'Loquesea', 'loquesea@gmai.com', 'LQS', 'Funcionario', NULL, NULL),
('345456576', '565456', 'Trs Strrr', 'Trsh', 'dord@gmail.com', 'DEED', 'Asociado', NULL, NULL),
('345456765', '', 'No Soy', 'Yo', 'yonosoy@yo.com', 'Yono', 'Estudiante', NULL, NULL),
('395476844', '583859', 'Medici Medici', 'Lorenzo', 'loren@gmail.com', 'Colegio de Abogados', 'Asociado', NULL, NULL),
('405323345', '', 'Salas Caldas', 'James', 'james.ca@gmail.com', 'Ninguna', 'Publico', NULL, NULL),
('453456745', '45634', 'Testy Testo', 'Test', 'tres@tres.com', 'Test', 'Asociado', NULL, NULL),
('456345678', '564345', 'Victoria Victorioso', 'Victor', 'victor@gmail.com', 'Colegio Victoria', 'Funcionario', NULL, NULL),
('495643245', '40567', 'Perez Zeledon', 'Pablo', 'pablo.pe@gmail.com', 'Institucion X', 'Funcionario', NULL, NULL),
('495665892', '576435', 'Helgen Bauer', 'Paul', 'p.helgen@gmail.com', 'Colegio de Abogados', 'Funcionario', 'Gty66!', 'admin'),
('496384312', '485834', 'Casas Salinas', 'Rogelio', 'roge.ca@gmail.com', 'Colegio de Abogados', 'Funcionario', 'thg55rd!U', 'admin'),
('545693245', '54566', 'Paredes Altas', 'Armando', 'armandoparedes@gmail.com', 'Colegio de Abogados', 'Asociado', NULL, NULL),
('546853467', '34523', 'Tesa Tesr', 'Test', 'test@test.com', 'Test', 'Estudiante', NULL, NULL),
('567765789', '', 'Locke', 'John', 'jlock@gmail.com', 'Ninguna', 'Publico', NULL, NULL),
('596405675', '456763', 'Puentes Loria', 'Carlos', 'ca.puen@gmail.com', 'Colegio de Abogados', 'Funcionario', 'gFh590-', 'admin'),
('656754567', '45534', 'Final Test', 'Final', 'fina@final.com', 'Finaa', 'Funcionario', NULL, NULL),
('657434567', '456743', 'Largo Dex', 'Franco', 'fran.la@gmail.com', 'Universidad CR', 'Publico', NULL, NULL),
('676545678', '54328', 'Perez Zeledon', 'Juan', 'juanpe@gmail.com', 'Colegio de Abogados', 'Funcionario', 'trrFg5&', 'admin'),
('685645676', '65687', 'Casas Cesar', 'Julio', 'julicc@gmail.com', 'Colegio de Abogados', 'Funcionario', 'tyeRR8%', 'admin'),
('765435645', '', 'Porras Mora', 'Werner', 'porras@gmail.com', 'UCR', 'Funcionario', NULL, NULL),
('765754365', '34643', 'Taca Todo', 'Test', 'test@test.com', 'Test', 'Estudiante', NULL, NULL),
('994535432', '', 'Plaza Costa', 'Maria', 'mari@gmail.com', 'UCR', 'Estudiante', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `visitas`
--

DROP TABLE IF EXISTS `visitas`;
CREATE TABLE IF NOT EXISTS `visitas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tema` varchar(150) DEFAULT NULL,
  `horaLlegada` timestamp NULL DEFAULT NULL,
  `horaSalida` timestamp NULL DEFAULT NULL,
  `usuario` char(9) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `usuario_idx` (`usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `visitas`
--

INSERT INTO `visitas` (`id`, `tema`, `horaLlegada`, `horaSalida`, `usuario`) VALUES
(1, 'Tesis', '2016-06-29 18:43:00', '2016-06-29 18:58:00', '496384312');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `consultabase`
--
ALTER TABLE `consultabase`
  ADD CONSTRAINT `fk_consultaBaseBDatos` FOREIGN KEY (`basedatos`) REFERENCES `basesdatos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_consultabase_consulta` FOREIGN KEY (`Consulta`) REFERENCES `consulta` (`id`);

--
-- Filtros para la tabla `prestamo`
--
ALTER TABLE `prestamo`
  ADD CONSTRAINT `fk_prestamoDocumento` FOREIGN KEY (`documento`) REFERENCES `documento` (`signatura`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_prestamoUsuario` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`cedula`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `telefono`
--
ALTER TABLE `telefono`
  ADD CONSTRAINT `pk_telef` FOREIGN KEY (`cedula`) REFERENCES `usuario` (`cedula`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `visitas`
--
ALTER TABLE `visitas`
  ADD CONSTRAINT `usuario` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`cedula`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
