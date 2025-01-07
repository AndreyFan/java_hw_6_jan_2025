-- liquibase formatted sql

-- changeset andrey:createUsers
CREATE TABLE Users (
UserID INT AUTO_INCREMENT NOT NULL,
Name VARCHAR(255) NULL,
Email VARCHAR(255) NULL,
PhoneNumber VARCHAR(255) NULL,
PasswordHash VARCHAR(255) NULL,
Role VARCHAR(255) NULL,
CONSTRAINT PK_USERS PRIMARY KEY (UserID));

