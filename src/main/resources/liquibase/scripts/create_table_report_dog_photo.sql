-- liquibase formatted sql

CREATE TABLE IF NOT EXISTS report_dog_text
(
    id        BIGSERIAL PRIMARY KEY,
    file_path text                                   NOT NULL,
    file_size bigint                                 NOT NULL,
    data      bytea                                  NOT NULL,
    user_id   BIGINT REFERENCES report_dog_text (id) NOT NULL
);