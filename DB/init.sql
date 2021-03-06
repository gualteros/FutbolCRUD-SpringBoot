DROP DATABASE IF EXISTS db;
DROP TABLE IF EXISTS db.Futbol;
 
CREATE DATABASE db;
USE db; 

CREATE TABLE Futbol (
id INT(9) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
Nombre VARCHAR(30) NOT NULL,
Ciudad VARCHAR(30) NOT NULL,
Propietario VARCHAR(50),
Capacidad LONG,
Division INT(1),
Competicion VARCHAR(50),
Numero_Jugadores INT(3),
Fecha_Fundacion TIMESTAMP, 
Fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO Futbol (Nombre, Ciudad, Propietario, Capacidad, Division, Competicion, Numero_Jugadores,Fecha_Fundacion)
VALUES  ("chelsea", "London", "Thomas Tuchel", 10000000, 1, "Premier League", 41, "10-06-26"),
		("Santa Fe", "Bogotá D.C.", "Perlun S.A.S.", 100000, 1, "Liga BetPlay", 41, "10-06-26"),
		("Liverpool", "Liverpool", "Fenway Sports Group", 10000022, 1, "Premier League", 31, "10-06-26");


SELECT * FROM db.Futbol;