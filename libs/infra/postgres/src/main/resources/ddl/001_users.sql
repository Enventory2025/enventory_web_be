CREATE TYPE auth_provider AS ENUM ('GOOGLE', 'GITHUB');

CREATE TABLE users (
   id UUID PRIMARY KEY,
   email VARCHAR(100) NOT NULL UNIQUE,
   provider auth_provider NOT NULL,
   provider_id VARCHAR(50) NOT NULL,
   created_at TIMESTAMPTZ NOT NULL,
   updated_at TIMESTAMPTZ NOT NULL
);
