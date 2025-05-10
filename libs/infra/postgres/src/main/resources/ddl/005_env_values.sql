CREATE TABLE env_values (
    id UUID PRIMARY KEY,
    env_key_id UUID NOT NULL,
    environment_id UUID NOT NULL,
    value TEXT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL
);

CREATE UNIQUE INDEX idx_env_values_env_key_environment ON env_values(env_key_id, environment_id);
