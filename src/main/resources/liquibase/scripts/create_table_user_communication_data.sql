-- liquibase formatted sql

-- changeset dzimin:1
CREATE TABLE IF NOT EXISTS user_communication_data
(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT,
    communication_data varchar(255) NOT NULL
);