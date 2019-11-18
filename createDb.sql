CREATE DATABASE IF NOT EXISTS goldBank;
USE goldBank;
DROP TABLE IF EXISTS Account;
CREATE TABLE Account (
    id int(100) NOT NULL AUTO_INCREMENT,
    client int(100) NOT NULL,
    balance double NOT NULL DEFAULT 0,
    PRIMARY KEY(id)
);
INSERT INTO Account (client) VALUES (1);