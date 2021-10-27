CREATE TABLE accounting.user
(
    ID SERIAL PRIMARY KEY,
    Name varchar(255) NOT NULL,
    Surname varchar(255) NOT NULL,
    Email varchar(255) NOT NULL,
    Password varchar(255) NOT NULL,
    Birth_Date timestamptz NOT NULL,
    Registration_Date timestamptz NOT NULL DEFAULT now()
);

CREATE TABLE accounting.currency
(
    ID SERIAL PRIMARY KEY,
    Name varchar(255) NOT NULL,
    Description varchar(255),

    User_ID int NOT NULL REFERENCES accounting.user(ID)
);

CREATE TABLE accounting.account
(
    ID SERIAL PRIMARY KEY,
    Name varchar(255) NOT NULL,
    Description varchar(255),

    User_ID int NOT NULL REFERENCES accounting.user(ID),
    Currency_ID int NOT NULL REFERENCES accounting.currency(ID)
);

CREATE TABLE accounting.category
(
    ID SERIAL PRIMARY KEY,
    Name varchar(255) NOT NULL,
    Category_type int NOT NULL,

    User_ID int NOT NULL REFERENCES accounting.user(ID)
);

CREATE TABLE accounting.item
(
    ID SERIAL PRIMARY KEY,
    Name varchar(255) NOT NULL,
    Date timestamptz NOT NULL DEFAULT now(),
    Value numeric(10, 2) NOT NULL,
    Comment varchar(255),

    Account_ID int NOT NULL REFERENCES accounting.account(ID),
    Category_ID int NOT NULL REFERENCES accounting.category(ID)
);