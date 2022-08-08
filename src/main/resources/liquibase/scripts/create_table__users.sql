-- liquibase formatted sql

DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id          BIGINT       NOT NULL,
    created_at  TIMESTAMP    NOT NULL,
    telegram_id BIGINT       NOT NULL,
    chat_id     BIGINT       NOT NULL,
    username    varchar(255) NOT NULL,
    first_name  varchar(255) NOT NULL,
    last_name   varchar(255) NULL,
    is_bot      BOOLEAN      NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE users
ADD COLUMN pet varchar(60) NOT NULL DEFAULT 'dog';