-- liquibase formatted sql

-- changeset alex2:ProductsIns1
INSERT INTO Products (Name, Description, Price, CategoryID, ImageURL, DiscountPrice, CreatedAt, UpdatedAt)
VALUES ('Milk', 'Fresh liter of milk', 1.2, 1, 'https://example.com/milk.jpg', 0.0, NOW(), NOW()),
        ('Bread', 'Fresh loaf of bread', 0.8, 1,  'https://example.com/bread.jpg', 10.0, NOW(), NOW()),
       ('Dishwashing Liquid', 'For cleaning dishes', 2.5, 2, 'https://example.com/detergent.jpg', 5.0, NOW(), NOW()),
       ('Glass Cleaner', 'Effective glass cleaner', 3.0, 2, 'https://example.com/glass_cleaner.jpg', 0.0, NOW(), NOW()),
       ('Television', 'LED TV', 150.0, 3, 'https://example.com/tv.jpg', 3.5, NOW(), NOW()),
       ('Radio', 'FM radio receiver', 30.0, 3, 'https://example.com/radio.jpg', 3.5, NOW(), NOW()),
       ('Teddy Bear', 'Plush teddy bear', 10.0, 4,'https://example.com/teddy_bear.jpg', 0.0, NOW(), NOW()),
       ('Building Blocks', 'Kids building blocks set', 15.0, 4, 'https://example.com/blocks.jpg', 0.0, NOW(), NOW()),
       ('T-Shirt', 'Graphic T-shirt', 12.0, 5, 'https://example.com/tshirt.jpg', 10.0, NOW(), NOW()),
       ('Jeans', 'Stylish jeans', 25.0, 5,'https://example.com/jeans.jpg', 20.0, NOW(), NOW()),
       ('Notebook', 'School notebook', 1.5, 6, 'https://example.com/notebook.jpg', 0.0, NOW(), NOW()),
       ('Pen', 'Gel pen', 0.5, 6,'https://example.com/pen.jpg', 0.0, NOW(), NOW())










