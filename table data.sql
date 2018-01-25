--Role
insert into role (name) values ('ROLE_ADMIN');
insert into role (name) values ('ROLE_USER');

--user
insert into users (email, first_name, last_name, password, username) values ('admin@test.com', 'Marshal', 'Lawalata', '$2a$12$y.di9hPEIZM.SyxXWwIr9.EHNQwhcdviNGvIwU6A1Z12sckYM24Jm', 'admin');
insert into user_role (user_id, role_id) values (1, 1);


--supplier
insert into address (city, line1, line2, postCode, state) values ('Ambon', 'Jl. Karpan 1', '', '347698', 'Jakarta')
insert into supplier (code, contact, email, name, address_id) values ('SU001', '0911-1239874', 'tokoindah@yahoo.co.id', 'Toko Indah', 1);

--product
insert into product (barcode, description, name, quantity, salesPrice, unit) values ('8999909028234', 'Rokok DJI SAM SOE KRETEK 12', 'DJI SAM SOE KRETEK 12', 20, 12000, 'Bungkus');
insert into product (barcode, description, name, quantity, salesPrice, unit) values ('8999909096004', 'Rokok SAMPOERNA MILD FILTER 16', 'SAMPOERNA MILD FILTER 16', 20, 20000, 'Bungkus');
insert into product (barcode, description, name, quantity, salesPrice, unit) values ('8997004301184', 'OISHI UDANG PEDAS 70GR', 'OISHI UDANG PEDAS 70GR', 20, 1200, 'Bungkus');
insert into product (barcode, description, name, quantity, salesPrice, unit) values ('8996196002954', 'PIATTOS RUMPUT LAUT 85GR', 'PIATTOS RUMPUT LAUT 85GR', 20, 5000, 'Bungkus');
insert into product (barcode, description, name, quantity, salesPrice, unit) values ('8997004301146', 'OISHI RIN BEE KEJU', 'OISHI RIN BEE KEJU', 20, 5500, 'Bungkus');
insert into product (barcode, description, name, quantity, salesPrice, unit) values ('8999999000066', 'TARO POTATO 40G', 'TARO POTATO 40G', 20, 7500, 'Bungkus');
insert into product (barcode, description, name, quantity, salesPrice, unit) values ('53314502210', 'KUSUKA BARBEQUE 60G', 'KUSUKA BARBEQUE 60G', 20, 1200, 'Bungkus');
