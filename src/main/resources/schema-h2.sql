DROP ALL OBJECTS;

CREATE TABLE `chatop_users` (
`chatop_id` INTEGER PRIMARY KEY AUTO_INCREMENT,
`chatop_name` VARCHAR(150) NOT NULL,
`chatop_email` VARCHAR(150) NOT NULL UNIQUE,
`chatop_password` VARCHAR(50) NOT NULL,
`created_at` DATE,
`updated_at` DATE );

CREATE TABLE `chatop_rentals` (
`chatop_id` INTEGER PRIMARY KEY AUTO_INCREMENT,
`chatop_name` VARCHAR(150) NOT NULL,
`chatop_surface` INTEGER NOT NULL,
`chatop_price` INTEGER NOT NULL,
`chatop_picture` TINYTEXT NOT NULL,
`chatop_description` TINYTEXT NOT NULL,
`owner_id` INTEGER NOT NULL,
`created_at` DATE,
`updated_at` DATE );

CREATE TABLE `chatop_messages` (
`chatop_id` INTEGER PRIMARY KEY AUTO_INCREMENT,
`chatop_message` TINYTEXT NOT NULL,
`chatop_user_id` INTEGER NOT NULL,
`chatop_rental_id` INTEGER NOT NULL
);