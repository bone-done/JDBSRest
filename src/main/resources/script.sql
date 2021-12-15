DROP DATABASE IF EXISTS web_project;

CREATE DATABASE IF NOT EXISTS web_project;
USE web_project;

CREATE TABLE IF NOT EXISTS user (
                                    id INT PRIMARY KEY AUTO_INCREMENT,
                                    email VARCHAR(45) NOT NULL UNIQUE,
                                    password VARCHAR(15) NOT NULL,
                                    first_name VARCHAR(50) NOT NULL,
                                    last_name VARCHAR(50),
                                    role ENUM('USER', 'ADMIN', 'GUEST') DEFAULT 'GUEST'
);

CREATE TABLE IF NOT EXISTS bucket (
                                      id INT PRIMARY KEY AUTO_INCREMENT,
                                      created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
                                      FOREIGN KEY(id) REFERENCES user(id)
);

CREATE TABLE IF NOT EXISTS product (
                                       id INT PRIMARY KEY AUTO_INCREMENT,
                                       name VARCHAR(255) NOT NULL,
                                       description VARCHAR(255) DEFAULT NULL,
                                       price DOUBLE NOT NULL
);

CREATE TABLE IF NOT EXISTS bucket_product(
                                             id INT PRIMARY KEY AUTO_INCREMENT,
                                             bucket_id INT NOT NULL,
                                             product_id INT NOT NULL,
                                             number INT NOT NULL DEFAULT 1,
                                             FOREIGN KEY(bucket_id) REFERENCES bucket(id),
                                             FOREIGN KEY(product_id) REFERENCES product(id)
);