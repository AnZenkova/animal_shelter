-- liquibase formatted sql

CREATE TABLE IF NOT EXISTS report_cat_photo
(
    id        BIGSERIAL PRIMARY KEY,
    file_path text                                   NOT NULL,
    file_size bigint                                 NOT NULL,
    data      bytea                                  NOT NULL,
    user_id   BIGINT REFERENCES report_cat_text (id) NOT NULL
);