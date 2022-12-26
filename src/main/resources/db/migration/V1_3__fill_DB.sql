insert into user_table
values
    ('5ce0e94e-055e-424d-96b4-11781092b6e6', 'admin@com.ua', 'firstName1', 'lastName1', '$2a$10$UGHNhQdhcyv/am2VS.nZHu/hGPng9yGxaXYgNMHWLWhz5YcoI92Gi', 'ADMIN'),
    ('f88570a8-1953-4452-9d39-03fbd9d58643', 'user@com.ua', 'firstName', 'lastName', '$2a$10$UGHNhQdhcyv/am2VS.nZHu/hGPng9yGxaXYgNMHWLWhz5YcoI92Gi', 'USER');

insert into manufacturers (
     id,
     name)
values

    ('1278dd1d-8fe5-4d5e-8407-d8a81f554150', 'ManufacturersTestName 1'),
    ('1579bb19-883e-424f-a5fb-446ce34e0825', 'ManufacturersTestName 0'),
    ('a12802e0-0ad9-4ab4-9274-8963f62dd21f', 'ManufacturersTestName 4'),
    ('d2fd3ced-9dc7-4a5f-90d2-584301fa5005', 'ManufacturersTestName 3'),
    ('ed837e9d-be74-4388-8afb-44090cdc93c9', 'ManufacturersTestName 2');

insert into products (
    id,
    name,
    price,
    manufacturers_id)
values
('043272ec-5d25-4609-902f-a4f2f2a87809', 'ProductTestName 4', '1115', 'a12802e0-0ad9-4ab4-9274-8963f62dd21f'),
('1934faa8-a0f6-40e2-8ff9-12719aec7de3', 'ProductTestName 3', '1114', '1278dd1d-8fe5-4d5e-8407-d8a81f554150'),
('c5b80419-6c07-4c57-bde3-72113fa066bb', 'ProductTestName 1', '1112', '1278dd1d-8fe5-4d5e-8407-d8a81f554150'),
('d0843e3f-4ca3-47e8-832d-a701a4674d35', 'ProductTestName 0', '1111', '1579bb19-883e-424f-a5fb-446ce34e0825'),
('fd70be49-8ade-4f52-8d5a-30a95cc1e3b0', 'ProductTestName 2', '1113', 'ed837e9d-be74-4388-8afb-44090cdc93c9');



