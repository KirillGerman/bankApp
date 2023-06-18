CREATE TABLE USERS (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(500),
    date_of_birth DATE,
    password VARCHAR(500)
);

CREATE TABLE ACCOUNT (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    user_id BIGINT references USERS(id),
    balance DECIMAL(12,2) CHECK (balance > 0),
    max_balance DECIMAL(12,2)
);

CREATE TABLE EMAIL_DATA (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    user_id BIGINT references USERS(id),
    email VARCHAR(200) UNIQUE
);

CREATE TABLE PHONE_DATA (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    user_id BIGINT references USERS(id),
    phone VARCHAR(13) UNIQUE
);