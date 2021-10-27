CREATE TABLE accounting.role
(
    ID          SERIAL PRIMARY KEY,
    Code        VARCHAR(255) NOT NULL
);

INSERT INTO accounting.role
VALUES
       (1, 'ADMIN'),
       (2, 'USER');

CREATE TABLE accounting.user_role
(
    ID          SERIAL PRIMARY KEY,
    User_id     INT NOT NULL REFERENCES accounting.user (ID),
    Role_id     INT NOT NULL REFERENCES accounting.role (ID)
);

CREATE UNIQUE index user_email_uniq_idx ON accounting.user (email);