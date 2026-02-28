CREATE TABLE IF NOT EXISTS accounts
(
    id         SERIAL CONSTRAINT accounts_pk PRIMARY KEY,
    nickname   VARCHAR(255),
    email      VARCHAR(255),
    password   VARCHAR(255),
    created_at TIMESTAMP DEFAULT now()
);