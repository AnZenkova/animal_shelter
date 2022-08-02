-- liquibase formatted sql

-- changeset dzimin:1
CREATE TABLE IF NOT EXISTS message_history
(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT,
    message text NOT NULL
);