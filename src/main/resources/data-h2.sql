INSERT INTO chatop_users
(chatop_id, chatop_name, chatop_email, chatop_password, created_at, updated_at)
VALUES
(1, 'Test User 1', 'test1@test.com', 'Test@1234', CURRENT_DATE, CURRENT_DATE),
(2, 'Test User 2', 'test2@test.com', 'Test1234!', CURRENT_DATE, CURRENT_DATE);

INSERT INTO chatop_rentals
(chatop_id, chatop_name, chatop_surface, chatop_price, chatop_picture, chatop_description, owner_id, created_at, updated_at)
VALUES
(1, 'Test House 1', 50, 25, '[\"http://picture1.jpeg\"]', 'Test description 1', 1, CURRENT_DATE, CURRENT_DATE),
(2, 'Test House 2', 300, 150, '[\"http://picture2.jpeg\"]', 'Test description 2', 2, CURRENT_DATE, CURRENT_DATE);