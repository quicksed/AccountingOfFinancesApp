CREATE TABLE accounting.role
(
    ID   SERIAL PRIMARY KEY,
    code VARCHAR(255) NOT NULL
);

INSERT INTO accounting.role
VALUES
       (1, 'admin'),
       (2, 'user');

CREATE TABLE accounting.user_role
(
    ID      SERIAL PRIMARY KEY,
    user_id INT NOT NULL
        REFERENCES accounting.user (id),
    role_id INT NOT NULL
        REFERENCES accounting.role (id)
);

CREATE UNIQUE index user_email_uniq_idx ON accounting.user (email);