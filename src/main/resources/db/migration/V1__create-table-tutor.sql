CREATE TABLE tutors
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    phone_number VARCHAR(13) NOT NULL,
    city VARCHAR(100) NOT NULL,
    personal_description TEXT
);