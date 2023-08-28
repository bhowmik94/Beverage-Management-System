INSERT INTO beverages (beverage_id, name, picture, price, alcohol_percent)
VALUES (1, 'Pepsi', 'https://i.postimg.cc/jjjwHQPv/pepsi.jpg', 5, 0.5);
INSERT INTO beverages (beverage_id, name, picture, price, alcohol_percent)
VALUES (2, 'CocaCola', 'https://i.postimg.cc/fbg3Btvs/coca-cola.jpg', 4, 0.5);
INSERT INTO beverages (beverage_id, name, picture, price, alcohol_percent)
VALUES (3, 'Fanta', 'https://i.postimg.cc/sXQM8jnh/fanta.jpg', 3, 0.5);
INSERT INTO beverages (beverage_id, name, picture, price, alcohol_percent)
VALUES (7, 'RedBull', 'https://as1.ftcdn.net/v2/jpg/03/53/40/50/1000_F_353405042_BalM14670f6CWmHjKZyzAxPKd3qCXlDY.jpg',
        1, 0.0);

INSERT INTO beverages (beverage_id, name, picture, price, alcohol_percent)
VALUES (4, 'Pepsi_crate', 'https://i.postimg.cc/jwc1mcDw/pepsi-crate.jpg', 5, 0.5);
INSERT INTO beverages (beverage_id, name, picture, price, alcohol_percent)
VALUES (5, 'Cocacola_crate', 'https://i.postimg.cc/BL4ymknf/coca-cola-crate.jpg', 4, 0.5);
INSERT INTO beverages (beverage_id, name, picture, price, alcohol_percent)
VALUES (6, 'Fanta_crate', 'https://i.postimg.cc/G9MNjM06/fanta-crate.webp', 3, 0.5);
INSERT INTO beverages (beverage_id, name, picture, price, alcohol_percent)
VALUES (8, 'RedBull', 'https://as1.ftcdn.net/v2/jpg/03/74/67/04/1000_F_374670440_yT2MUuFvchO6FR5QfIsRKMxAQbgRo0Ad.jpg',
        1, 0.0);

INSERT INTO bottles (bottle_id, volume, supplier, stock, beverage_id)
VALUES (1, 0.5, 'Pepsi', 10, 1);
INSERT INTO bottles (bottle_id, volume, supplier, stock, beverage_id)
VALUES (2, 0.5, 'CocaCola', 10, 2);
INSERT INTO bottles (bottle_id, volume, supplier, stock, beverage_id)
VALUES (3, 0.5, 'Fanta', 20, 3);
INSERT INTO bottles (bottle_id, volume, supplier, stock, beverage_id)
VALUES (7, 2, 'RedBull', 30, 7);


INSERT INTO crates (crate_id, no_of_bottles, creates_in_stock, bottle_id, crate_beverage_id)
VALUES (1, 10, 10, 1, 4);
INSERT INTO crates (crate_id, no_of_bottles, creates_in_stock, bottle_id, crate_beverage_id)
VALUES (2, 10, 10, 2, 5);
INSERT INTO crates (crate_id, no_of_bottles, creates_in_stock, bottle_id, crate_beverage_id)
VALUES (3, 10, 5, 3, 6);
INSERT INTO crates (crate_id, no_of_bottles, creates_in_stock, bottle_id, crate_beverage_id)
VALUES (7, 20, 10, 7, 8);
