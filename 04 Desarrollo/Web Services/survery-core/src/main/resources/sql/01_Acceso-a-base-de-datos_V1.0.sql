﻿/*
Script de acceso a base de datos db_survery

-- MODIFICACIONES
1. Crea base de datos db_survery
1. Creación de usuario de acceso a la base de datos db_survery

-- DESHACER
use db_survery;

-- usuario
DROP USER 'user_survery'@'%', 'user_survery'@'localhost';
DROP DATABASE db_survery;
*/

CREATE DATABASE IF NOT EXISTS db_survery;

-- Crea el usuario y asigna privilegios
GRANT ALL PRIVILEGES ON infonavit.* TO 'user_survery'@'%' 			  IDENTIFIED BY 'usr_svry*2017' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON infonavit.* TO 'user_survery'@'localhost' IDENTIFIED BY 'usr_svry*2017' WITH GRANT OPTION;