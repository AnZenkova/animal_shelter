-- liquibase formatted sql

CREATE TABLE IF NOT EXISTS message_history
(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT,
    message text NOT NULL
);