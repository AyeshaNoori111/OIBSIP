CREATE DATABASE mcq_system;

USE mcq_system;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(50),
    fullname VARCHAR(50),
    email VARCHAR(50)
);

CREATE TABLE questions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    question_text VARCHAR(255),
    option1 VARCHAR(100),
    option2 VARCHAR(100),
    option3 VARCHAR(100),
    option4 VARCHAR(100),
    correct_option INT
);

CREATE TABLE results (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    score INT,
    submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
INSERT INTO questions (question_text, option1, option2, option3, option4, correct_option) VALUES
('What is the capital of India?', 'Mumbai', 'New Delhi', 'Chennai', 'Kolkata', 2),

('Which language is used for Android development?', 'Java', 'Python', 'C++', 'Swift', 1),

('Which company developed Java?', 'Microsoft', 'Sun Microsystems', 'Google', 'IBM', 2),

('Which keyword is used to inherit a class in Java?', 'this', 'super', 'extends', 'implements', 3),

('Which method is the entry point of a Java program?', 'start()', 'main()', 'run()', 'init()', 2);
INSERT INTO users (username, password, fullname, email) VALUES
('admin', 'admin123', 'Admin User', 'admin@gmail.com');
SELECT * FROM users;
SELECT * FROM questions;
SELECT * FROM results;