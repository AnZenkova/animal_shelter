-- liquibase formatted sql

-- changeset lancoid:1

DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id          INT          NOT NULL,
    created_at  TIMESTAMP    NOT NULL,
    telegram_id INT          NOT NULL,
    chat_id     INT          NOT NULL,
    username    varchar(255) NOT NULL,
    first_name  varchar(255) NOT NULL,
    last_name   varchar(255) NOT NULL,
    is_bot      BOOLEAN      NOT NULL,
    PRIMARY KEY (id)
);