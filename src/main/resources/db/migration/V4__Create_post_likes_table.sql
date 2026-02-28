CREATE TABLE IF NOT EXISTS post_likes
(
    id serial PRIMARY KEY,

    post_id integer NOT NULL
    REFERENCES posts(id) ON DELETE CASCADE,

    account_id integer NOT NULL
    REFERENCES accounts(id) ON DELETE CASCADE,

    created_at timestamp with time zone NOT NULL DEFAULT now(),

    CONSTRAINT post_likes_post_account_uk
    UNIQUE (post_id, account_id)
);