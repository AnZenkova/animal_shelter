-- liquibase formatted sql

CREATE TABLE IF NOT EXISTS report_dog_text
(
    id               BIGSERIAL PRIMARY KEY,
    diet             text                              NOT NULL,
    wellBeing        text                              NOT NULL,
    changeInBehavior text                              NOT NULL,
    user_id          BIGINT REFERENCES users_dogs (id) NOT NULL
);