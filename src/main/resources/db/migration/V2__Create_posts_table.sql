CREATE TABLE IF NOT EXISTS posts
(
    id          SERIAL CONSTRAINT posts_pk PRIMARY KEY,
    title       VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    created_at  TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    account_id  INTEGER CONSTRAINT posts_accounts_id_fk REFERENCES accounts,
    images      VARCHAR NOT NULL
);

