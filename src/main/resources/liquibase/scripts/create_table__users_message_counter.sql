-- liquibase formatted sql

-- changeset lancoid:1

DROP TABLE IF EXISTS users_message_counter;

CREATE TABLE user_message_counter
(
    id      INT NOT NULL,
    user_id INT NOT NULL,
    counter INT NOT NULL,
    PRIMARY KEY (id)
);