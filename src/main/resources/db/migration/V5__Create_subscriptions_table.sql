CREATE TABLE IF NOT EXISTS subscriptions (
    follower_id INTEGER NOT NULL,
    following_id INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (follower_id, following_id),
    CONSTRAINT check_self_subscription CHECK (follower_id <> following_id)
    );