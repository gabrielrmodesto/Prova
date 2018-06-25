CREATE DATABASE compras;
USE compras;

CREATE TABLE fornecedor(
	fx int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	f_nome varchar(100) NOT NULL,
	f_situacao varchar(50) NOT NULL,
	f_cidade varchar(50) NOT NULL
);