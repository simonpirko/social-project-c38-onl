CREATE TABLE IF NOT EXISTS post_comments
(
    id         SERIAL PRIMARY KEY,
    account_id INTEGER       NOT NULL REFERENCES accounts (id) ON DELETE CASCADE,
    post_id    INTEGER       NOT NULL REFERENCES posts (id) ON DELETE CASCADE,
    text       VARCHAR(1000) NOT NULL,
    created_at TIMESTAMP DEFAULT now()
);

CREATE INDEX IF NOT EXISTS idx_post_comments_post_id ON post_comments (post_id);
CREATE INDEX IF NOT EXISTS idx_post_comments_account_id ON post_comments (account_id);