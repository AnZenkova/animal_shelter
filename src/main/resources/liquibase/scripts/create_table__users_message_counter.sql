-- liquibase formatted sql

DROP TABLE IF EXISTS users_message_counter;

CREATE TABLE user_message_counter
(
    id      BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    counter INT NOT NULL,
    PRIMARY KEY (id)
);