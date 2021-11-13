CREATE DATABASE db_Vulcano;

USE db_Vulcano;

CREATE TABLE User(
    Id INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(60) NOT NULL,
	UserName VARCHAR(30) NOT NULL,
	Email VARCHAR(30) NOT NULL,
	Password VARCHAR(20) NOT NULL,
	ContactNumber VARCHAR(15) NOT NULL
);

CREATE TABLE Project(
	Id INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(60) NOT NULL,
    Description TEXT NOT NULL,
    Owner_id INT,
    FOREIGN KEY(Owner_id) REFERENCES User(Id) 
);

CREATE TABLE Requirements(
	Id INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(60) NOT NULL,
    Module VARCHAR(60) NOT NULL,
    Feature TEXT NOT NULL,
    CreationDate DATETIME NOT NULL,
    Author_id INT,
    LastChange TIMESTAMP NOT NULL,
    LastChangeAuthor_id INT,
    Version VARCHAR(10) NOT NULL,
    Priority ENUM('Baixa','Média','Alta','Crítico') DEFAULT 'Baixa',
    Complexity ENUM('Baixa','Média','Alta','Crítico') DEFAULT 'Baixa',
    EstimatedHours INT,
    State ENUM('Backlog','To Do','In progress','Done') DEFAULT 'Backlog',
    Phase VARCHAR(30),
    Description TEXT,
    Project_id INT,
    FOREIGN KEY(project_id) REFERENCES Project(Id),
    FOREIGN KEY(Author_id) REFERENCES User(Id),
    FOREIGN KEY(LastChangeAuthor_id) REFERENCES User(Id)
);