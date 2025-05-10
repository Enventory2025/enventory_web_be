CREATE TABLE env_stores (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    name VARCHAR(30) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL
);

CREATE UNIQUE INDEX idx_env_stores_user_name ON env_stores(user_id, name);
