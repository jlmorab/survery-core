/*
Script de creación de tablas para la funcionalidad de Encuestas

-- MODIFICACIONES
1. Crea tabla catalogo de estatus de registros
	1.1 Crea registros iniciales

-- DESHACER
use db_survery;

-- Elimina tablas
DROP TABLE IF EXISTS cat_opcion_pregunta;
DROP TABLE IF EXISTS cat_pregunta;
DROP TABLE IF EXISTS cat_encuesta;
DROP TABLE IF EXISTS cat_tipo_encuesta;
DROP TABLE IF EXISTS sys_status_general;
*/

USE db_survery;

-- Crea tabla sys_status_general |: Catálogo de estatus de registro
CREATE TABLE sys_status_general(
	i_status_general 				TINYINT(1) 		UNSIGNED NOT NULL AUTO_INCREMENT 	COMMENT 'Id',
	n_status_general 				VARCHAR(50) 	COLLATE utf8_unicode_ci NOT NULL 	COMMENT 'Nombre',
	CONSTRAINT 					PK_status_general
	PRIMARY 						KEY(i_status_general)
) COMMENT='Catálogo de estatus de los registros'
ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO sys_status_general VALUES
	(1, 'Vigente'),
    (2, 'Suspendido'), 
    (3, 'Cancelado');
    
-- Crea tabla cat_tipo_encuesta |: Catálogo de tipos de encuesta
CREATE TABLE cat_tipo_encuesta(
	i_tipo_encuesta 				TINYINT(1) 		UNSIGNED NOT NULL AUTO_INCREMENT 	COMMENT 'Id',
    i_tipo_encuesta_status 			TINYINT(1) 		UNSIGNED NOT NULL 					COMMENT 'Estatus de registro', 
    n_tipo_encuesta 				VARCHAR(50) 	COLLATE utf8_unicode_ci NOT NULL 	COMMENT 'Nombre', 
    CONSTRAINT 						PK_tipo_encuesta 
    PRIMARY 						KEY(i_tipo_encuesta), 
    CONSTRAINT 						FK_tipo_encuesta_status 
    FOREIGN 						KEY(i_tipo_encuesta_status) 
    REFERENCES 						sys_status_general(i_status_general)
) COMMENT 'Catálogo de tipos de encuesta' 
ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf_unicode_ci;

INSERT INTO cat_tipo_encuesta VALUES
	(1, 1, 'Abierta'), 
    (2, 1, 'Opción multiple'), 
    (3, 1, 'Selección múltiple');
    
-- Crea tabla cat_encuesta |: Catálogo de encuestas
CREATE TABLE cat_encuesta(
	i_encuesta 						SMALLINT(6) 	UNSIGNED NOT NULL AUTO_INCREMENT 	COMMENT 'Id',
    i_encuesta_status 				TINYINT(1) 		UNSIGNED NOT NULL 					COMMENT 'Estatus de registro',
    i_encuesta_tipo_encuesta 		TINYINT(1) 		UNSIGNED NOT NULL 					COMMENT 'Tipo de encuesta', 
    n_encuesta 						VARCHAR(100) 	COLLATE utf8_unicode_ci NOT NULL 	COMMENT 'Nombre', 
    CONSTRAINT 						PK_encuesta 
    PRIMARY 						KEY(i_encuesta), 
    CONSTRAINT 						FK_encuesta_status 
    FOREIGN 						KEY(i_encuesta_status) 
    REFERENCES 						sys_status_general(i_status_general), 
    CONSTRAINT 						FK_encuesta_tipo_encuesta 
    FOREIGN 						KEY(i_encuesta_tipo_encuesta) 
    REFERENCES 						cat_tipo_encuesta(i_tipo_encuesta)
) COMMENT 'Catálogo de encuestas' 
ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Crea tabla cat_pregunta |: Catálogo de preguntas
CREATE TABLE cat_pregunta(
	i_pregunta 						INT(11) 		UNSIGNED NOT NULL AUTO_INCREMENT 	COMMENT 'Id', 
	i_pregunta_status 				TINYINT(1) 		UNSIGNED NOT NULL 					COMMENT 'Estatus de registro', 
	i_pregunta_encuesta 			SMALLINT(6) 	UNSIGNED NOT NULL 					COMMENT 'Encuesta', 
	q_pregunta_orden 				SMALLINT(3) 	NOT NULL 							COMMENT 'Orden', 
	n_pregunta 						VARCHAR(255) 	COLLATE utf8_unicode_ci NOT NULL 	COMMENT 'Pregunta', 
    CONSTRAINT 						PK_pregunta 
    PRIMARY 						KEY(i_pregunta), 
    CONSTRAINT 						FK_pregunta_status 
    FOREIGN 						KEY(i_pregunta_status) 
    REFERENCES 						sys_status_general(i_status_general), 
    CONSTRAINT 						FK_pregunta_encuesta 
    FOREIGN 						KEY(i_pregunta_encuesta) 
    REFERENCES 						cat_encuesta(i_encuesta)
) COMMENT 'Catálogo de preguntas' 
ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Crea tabla cat_opcion_pregunta |: Catálogo de opciones de pregunta
CREATE TABLE cat_opcion_pregunta(
	i_opcion_pregunta 				INT(11) 		UNSIGNED NOT NULL AUTO_INCREMENT 	COMMENT 'Id',
	i_opcion_pregunta_status 		TINYINT(1) 		UNSIGNED NOT NULL 					COMMENT 'Estatus de registro', 
	i_opcion_pregunta_pregunta 		INT(11) 		UNSIGNED NOT NULL 					COMMENT 'Pregunta', 
	n_opcion_pregunta 				VARCHAR(255) 	COLLATE utf8_unicode_ci NOT NULL 	COMMENT 'Nombre', 
	f_opcion_pregunta_correcto 		TINYINT(1) 		UNSIGNED NOT NULL DEFAULT 0 		COMMENT 'Correcto',
	CONSTRAINT 						PK_opcion_pregunta 
	PRIMARY 						KEY(i_opcion_pregunta), 
	CONSTRAINT 						FK_opcion_pregunta_status 
	FOREIGN 						KEY(i_opcion_status) 
	REFERENCES 						sys_status_general(i_status_general), 
	CONSTRAINT 						FK_opcion_pregunta_pregunta 
	FOREIGN 						KEY(i_opcion_pregunta_pregunta) 
	REFERENCES 						cat_pregunta(i_pregunta)
) COMMENT 'Catálogo de opciones de pregunta' 
ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;