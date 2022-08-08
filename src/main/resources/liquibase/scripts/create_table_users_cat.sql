-- liquibase formatted sql

DROP TABLE IF EXISTS users_cats;

CREATE TABLE users_cats
(
    id          BIGINT       NOT NULL,
    username    varchar(255) NULL,
    first_name  varchar(255) NOT NULL,
    last_name   varchar(255) NULL,
    PRIMARY KEY (id)
);