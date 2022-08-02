DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id          BIGINT       NOT NULL,
    created_at  TIMESTAMP    NULL,
    telegram_id BIGINT       NOT NULL,
    chat_id     BIGINT       NOT NULL,
    username    varchar(255) NULL,
    first_name  varchar(255) NOT NULL,
    last_name   varchar(255) NULL,
    is_bot      BOOLEAN      NOT NULL,
    PRIMARY KEY (id)
);