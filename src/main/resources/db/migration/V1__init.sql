CREATE TABLE accounting.user
(
    ID                  SERIAL PRIMARY KEY,
    Name                VARCHAR(255) NOT NULL,
    Surname             VARCHAR(255) NOT NULL,
    Email               VARCHAR(255) NOT NULL,
    Password            VARCHAR(255) NOT NULL,
    Birth_Date          TIMESTAMPTZ NOT NULL,
    Registration_Date   TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE accounting.currency
(
    ID                  SERIAL PRIMARY KEY,
    Name                VARCHAR(255) NOT NULL,
    Description         VARCHAR(255),

    User_ID             INT NOT NULL REFERENCES accounting.user(ID)
);

CREATE TABLE accounting.account
(
    ID                  SERIAL PRIMARY KEY,
    Name                VARCHAR(255) NOT NULL,
    Description         VARCHAR(255),

    User_ID             INT NOT NULL REFERENCES accounting.user(ID),
    Currency_ID         INT NOT NULL REFERENCES accounting.currency(ID)
);

CREATE TABLE accounting.category
(
    ID                  SERIAL PRIMARY KEY,
    Name                VARCHAR(255) NOT NULL,
    Category_type       INT NOT NULL,

    User_ID             INT NOT NULL REFERENCES accounting.user(ID)
);

CREATE TABLE accounting.item
(
    ID                   SERIAL PRIMARY KEY,
    Name                 VARCHAR(255) NOT NULL,
    Date                 TIMESTAMPTZ NOT NULL DEFAULT now(),
    Value                NUMERIC(10, 2) NOT NULL,
    Comment              VARCHAR(255),

    Account_ID           INT NOT NULL REFERENCES accounting.account(ID),
    Category_ID          INT NULL REFERENCES accounting.category(ID)
);