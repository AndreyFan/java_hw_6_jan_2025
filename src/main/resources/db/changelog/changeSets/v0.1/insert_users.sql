-- liquibase formatted sql

-- changeset andrey:usersIns1
INSERT INTO Users (Name, Email, PhoneNumber, PasswordHash, Role)
VALUES
('Peter', 'peter@gmail.com', '+4915127773335', 'PeterPass', 'ADMINISTRATOR'),
('Olga', 'Olga@gmail.com', '+49151277733777', 'OlgaPass', 'CLIENT'),
('Mark', 'mark@gmail.com', '+4915125553335', 'MarkPass', 'CLIENT'),
('Oksana', 'oksana@gmail.com', '+4915117773335', 'oksanaPass', 'CLIENT'),
('Anatolii', 'anatolii@gmail.com', '+4915127773377', 'AnatoliiPass', 'CLIENT');
