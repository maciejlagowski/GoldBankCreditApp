CREATE DATABASE IF NOT EXISTS goldBank;
USE goldBank;
DROP TABLE IF EXISTS Account;
CREATE TABLE Account (
    id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    client bigint(20) NOT NULL,
    balance double NOT NULL DEFAULT 0
);
CREATE TABLE Client (
    id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    forename VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    pesel bigint(20) NOT NULL,
    idNumber VARCHAR(255) NOT NULL
);
