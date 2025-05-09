CREATE TABLE users (
   id UUID PRIMARY KEY,       -- UUID 자동 생성
   email VARCHAR(255) NOT NULL UNIQUE,                  -- 고유 이메일
   provider VARCHAR(50) NOT NULL,                       -- enum name 저장 (e.g. GOOGLE)
   provider_id VARCHAR(255) NOT NULL,                   -- OAuth 또는 Social ID
   created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),       -- 생성 시간
   updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()        -- 수정 시간
);
