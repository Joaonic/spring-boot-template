---------------------------
-- START OF DATABASE
---------------------------
CREATE SCHEMA IF NOT EXISTS app;

CREATE
    EXTENSION IF NOT EXISTS unaccent;

------------------------------
-- POSTERR
------------------------------
CREATE TABLE app.user
(
    id         SERIAL,
    email      CHARACTER VARYING(50) UNIQUE NOT NULL,
    username   CHARACTER VARYING(20) UNIQUE NOT NULL,
    password   CHARACTER VARYING(128),
    created_at TIMESTAMP WITHOUT TIME ZONE  NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    deleted_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_user PRIMARY KEY (id),
    CONSTRAINT un_user_email UNIQUE (email),
    CONSTRAINT un_user_username UNIQUE (username),
    CONSTRAINT username_alphanumeric_only CHECK ( username ~* '^[a-zA-Z0-9]+$' ),
    CONSTRAINT valid_email CHECK (email ~* '^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$')
);

COMMENT
    ON TABLE app.user IS 'Entity responsible for storing users';
COMMENT
    ON COLUMN app.user.id IS 'Unique entity identifier';
COMMENT
    ON COLUMN app.user.email IS 'Users email, used for conventional login';
COMMENT
    ON COLUMN app.user.username IS 'Username, used for conventional login';
COMMENT
    ON COLUMN app.user.password IS 'User access password by conventional login';
COMMENT
    ON COLUMN app.user.created_at IS 'User registration data';
COMMENT
    ON COLUMN app.user.updated_at IS 'Date of last users update';
COMMENT
    ON COLUMN app.user.deleted_at IS 'Date when user was deactivated';
