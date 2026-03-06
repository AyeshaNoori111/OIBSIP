CREATE DATABASE reservation_db;
USE reservation_db;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50),
    password VARCHAR(50)
);

INSERT INTO users (username, password)
VALUES ('admin', '1234');

CREATE TABLE reservations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    train_no VARCHAR(20),
    class_type VARCHAR(20),
    from_place VARCHAR(50),
    to_place VARCHAR(50),
    journey_date VARCHAR(20),
    pnr VARCHAR(20)
);
select * from reservations;