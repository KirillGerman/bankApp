CREATE SEQUENCE hibernate_sequence
INCREMENT 1
START 1;


CREATE TABLE USERS (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(500),
    date_of_birth DATE,
    password VARCHAR(500)
);

CREATE TABLE ACCOUNT (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    user_id BIGINT references USERS(id),
    balance DECIMAL(12,2) CHECK (balance > 0)
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