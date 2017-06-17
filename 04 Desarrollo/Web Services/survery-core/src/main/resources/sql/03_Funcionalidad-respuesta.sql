/*
Script de creación de tablas para la funcionalidad de Respuestas

-- MODIFICACIONES
1. Crea tabla de registro de encuestados
1. Crea tabla de registro de respuestas

-- DESHACER
use db_survery;

-- Elimina tablas
DROP TABLE IF EXISTS tra_respuesta;
DROP TABLE IF EXISTS tra_encuestado;
*/

USE db_survery;

-- Crea tabla tra_encuestado |: Registro de encuestados
CREATE TABLE tra_encuestado(
	i_encuestado 					INT(11) 		UNSIGNED NOT NULL AUTO_INCREMENT 	COMMENT 'Id',
	i_encuestado_status 			TINYINT(1) 		UNSIGNED NOT NULL 					COMMENT 'Estatus de registro', 
	i_encuestado_encuesta 			SMALLINT(6) 	UNSIGNED NOT NULL 					COMMENT 'Encuesta',
	n_encuestado_contacto 			VARCHAR(100) 	COLLATE utf8_unicode_ci NOT NULL 	COMMENT 'Nombre de contacto',
	x_encuestado_email 				VARCHAR(100) 	COLLATE utf8_unicode_ci 			COMMENT 'Correo electrónico', 
	d_encuestado 					DATETIME 		NOT NULL 							COMMENT 'Fecha y hora de creación',
	CONSTRAINT 						PK_encuestado
	PRIMARY 						KEY(i_encuestado), 
	CONSTRAINT 						FK_encuestado_status 
	FOREIGN 						KEY(i_encuestado_status) 
	REFERENCES 						sys_status_general(i_status_general), 
	CONSTRAINT 						FK_encuestado_encuesta 
	FOREIGN 						KEY(i_encuestado_encuesta) 
	REFERENCES 						cat_encuesta(i_encuesta)
) COMMENT='Registro de encuestados'
ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Crea tabla tra_respuesta |: Registro de respuestas
CREATE TABLE tra_respuesta(
	i_respuesta 					BIGINT(20) 		UNSIGNED NOT NULL AUTO_INCREMENT 	COMMENT 'Id',
	i_respuesta_status 				TINYINT(1) 		UNSIGNED NOT NULL 					COMMENT 'Estatus de registro',
	i_respuesta_encuestado 			INT(11) 		UNSIGNED NOT NULL 					COMMENT 'Encuestado', 
	i_respuesta_pregunta 			INT(11) 		UNSIGNED NOT NULL 					COMMENT 'Pregunta', 
	i_respuesta_opcion_pregunta 	INT(11) 		UNSIGNED NOT NULL 					COMMENT 'Opción de pregunta', 
	x_respuesta 					TEXT 												COMMENT 'Respuesta abierta', 
	CONSTRAINT 						PK_respuesta 
	PRIMARY 						KEY(i_respuesta), 
	CONSTRAINT 						FK_respuesta_status 
	FOREIGN 						KEY(i_respuesta_status) 
	REFERENCES 						sys_status_general(i_status_general), 
	CONSTRAINT 						FK_respuesta_encuestado 
	FOREIGN 						KEY(i_respuesta_encuestado) 
	REFERENCES 						tra_encuestado(i_encuestado), 
	CONSTRAINT 						FK_respuesta_pregunta 
	FOREIGN 						KEY(i_respuesta_pregunta) 
	REFERENCES 						cat_pregunta(i_pregunta), 
	CONSTRAINT 						FK_respuesta_opcion_pregunta 
	FOREIGN 						KEY(i_respuesta_opcion_pregunta) 
	REFERENCES 						cat_opcion_pregunta(i_opcion_pregunta)
) COMMENT='Registro de respuestas'
ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;