CREATE TABLE env_keys (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    env_store_id UUID NOT NULL,
    name VARCHAR(30) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL
);

CREATE UNIQUE INDEX idx_env_keys_env_store_name ON env_keys(env_store_id, name);
