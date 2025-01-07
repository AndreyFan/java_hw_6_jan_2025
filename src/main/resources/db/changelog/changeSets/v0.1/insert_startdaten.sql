-- liquibase formatted sql

-- changeset andrey:favoritesIns1
INSERT INTO Favorites (UserID, ProductID)
VALUES
(1, 10),
(1, 5),
(1, 12),
(2, 5),
(3, 10);

INSERT INTO Cart (UserID)
VALUES
(1),
(2),
(3),
(4),
(5);

INSERT INTO CartItems (CartID, ProductID, Quantity)
VALUES
(1, 5, 1),
(2, 1, 12),
(2, 3, 2 ),
(2, 10, 1),
(5, 10, 1);

INSERT INTO Orders (UserID, CreatedAt, DeliveryAddress, ContactPhone, DeliveryMethod, Status, UpdatedAt)
VALUES
(2, NOW(), 'Berlin , Barbarossa Str., 4', '+4915112456878', 'Germes', 'COMPLETED', NOW()),
(3, NOW(), 'Brandeburg , Berliner Str., 15', '+491211844796', 'HL', 'ONTHEWAY', NOW()),
(4, NOW(), 'Hoff , Bismarc Str., 7 ', '+4912115656777', 'Germes', 'COMPLETED', NOW() );

INSERT INTO OrderItems (OrderID, ProductID, Quantity, PriceAtPurchase)
VALUES
(1, 2, 2, 0.8),
(2, 5, 1, 150.0),
(3, 10, 2, 25.0);
