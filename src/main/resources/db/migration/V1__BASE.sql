---------------------------
-- START OF DATABASE
---------------------------
CREATE SCHEMA IF NOT EXISTS auth;

CREATE
    EXTENSION IF NOT EXISTS unaccent;

------------------------------
-- POSTERR
------------------------------
CREATE TABLE auth.user
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
    ON TABLE auth.user IS 'Entity responsible for storing users';
COMMENT
    ON COLUMN auth.user.id IS 'Unique entity identifier';
COMMENT
    ON COLUMN auth.user.email IS 'Users email, used for conventional login';
COMMENT
    ON COLUMN auth.user.username IS 'Username, used for conventional login';
COMMENT
    ON COLUMN auth.user.password IS 'User access password by conventional login';
COMMENT
    ON COLUMN auth.user.created_at IS 'User registration data';
COMMENT
    ON COLUMN auth.user.updated_at IS 'Date of last users update';
COMMENT
    ON COLUMN auth.user.deleted_at IS 'Date when user was deactivated';

-- password 'password'

INSERT INTO auth.user (username, email, password)
VALUES ('test', 'test@email.com',
        '$2a$10$cZAGvsVSFNoIoL4c5G.jDu4JNB700zC.tbJQMGA3yVuNA.gU98Nhm');

CREATE TABLE auth.role
(
    id   bigserial primary key,
    name varchar(255) NOT NULL,
    CONSTRAINT uk_role_name UNIQUE (name)
);

CREATE TABLE auth.privilege
(
    id   bigserial primary key,
    name varchar(255) NOT NULL,
    CONSTRAINT uk_privilege_name UNIQUE (name)
);

CREATE TABLE auth.roles_privileges
(
    privilege_id bigint NOT NULL,
    role_id      bigint NOT NULL,
    PRIMARY KEY (privilege_id, role_id),
    CONSTRAINT fk_roles_privileges_role_id FOREIGN KEY (role_id) REFERENCES auth.role (id),
    CONSTRAINT fk_roles_privileges_privilege_id FOREIGN KEY (privilege_id) REFERENCES auth.privilege (id)
);
CREATE TABLE auth.user_roles
(
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_roles_role_id FOREIGN KEY (role_id) REFERENCES auth.role (id),
    CONSTRAINT fk_user_roles_user_id FOREIGN KEY (user_id) REFERENCES auth.user (id)
);

INSERT INTO auth.role(name)
VALUES ('ROLE_USER');
INSERT INTO auth.role(name)
VALUES ('ROLE_ADMIN');

INSERT INTO auth.privilege(name)
VALUES ('READ_PRIVILEGE');
INSERT INTO auth.privilege(name)
VALUES ('WRITE_PRIVILEGE');

INSERT INTO auth.roles_privileges(privilege_id, role_id)
values ((select id from auth.privilege where name = 'READ_PRIVILEGE'),
        (select id from auth.role where name = 'ROLE_USER'));

INSERT INTO auth.roles_privileges(privilege_id, role_id)
values ((select id from auth.privilege where name = 'READ_PRIVILEGE'),
        (select id from auth.role where name = 'ROLE_ADMIN'));

INSERT INTO auth.roles_privileges(privilege_id, role_id)
values ((select id from auth.privilege where name = 'WRITE_PRIVILEGE'),
        (select id from auth.role where name = 'ROLE_ADMIN'));

INSERT INTO auth.user_roles(user_id, role_id)
values ((select id from auth.user where username = 'test'),
        (select id from auth.role where name = 'ROLE_ADMIN'));