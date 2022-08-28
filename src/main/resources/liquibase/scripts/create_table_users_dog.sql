-- liquibase formatted sql

DROP TABLE IF EXISTS users_dogs;

CREATE TABLE users_dogs
(
    id          BIGINT       NOT NULL,
    username    varchar(255) NULL,
    first_name  varchar(255) NOT NULL,
    last_name   varchar(255) NULL,
    PRIMARY KEY (id)
);