DROP DATABASE IF EXISTS kraps;
CREATE DATABASE kraps
	CHARACTER SET utf8
	COLLATE utf8_general_ci;
USE kraps;

CREATE TABLE users (
	id INTEGER AUTO_INCREMENT,
    username NVARCHAR(60) NOT NULL,
    email NVARCHAR(60) NOT NULL,
    password NVARCHAR(60) NOT NULL,
    IBAN VARCHAR(25) NOT NULL,
    driving_license_photo_url NVARCHAR(255) NOT NULL,
    
    PRIMARY KEY(id)
);

CREATE TABLE waiting_for_approval (
	id INTEGER AUTO_INCREMENT,
    username NVARCHAR(60),
    email NVARCHAR(60) NOT NULL,
    password NVARCHAR(60) NOT NULL,
    IBAN VARCHAR(25) NOT NULL,
    driving_license_photo_url VARCHAR(255) NOT NULL,
    
    PRIMARY KEY(id)
);

CREATE TABLE vouchers (
	user_id INTEGER NOT NULL,
    voucher_type ENUM ('free_ride', 'free_day') NOT NULL,
    date_given DATE NOT NULL,
    expiring_date DATE NOT NULL,
    
    FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE staff (
	id INTEGER AUTO_INCREMENT,
    first_name NVARCHAR(50),
    last_name NVARCHAR(50),
    position NVARCHAR(50),
    hiring_date DATE,
    
    PRIMARY KEY(id)
);

CREATE TABLE cars (
	registration_number VARCHAR(8),
    brand NVARCHAR(20),
    model NVARCHAR(20),
    
    PRIMARY KEY(registration_number)
);

CREATE TABLE cars_statistics (
	car VARCHAR(8) NOT NULL UNIQUE,
    battery_percentage TINYINT NOT NULL,
    mileage INTEGER NOT NULL,
    
    FOREIGN KEY (car) REFERENCES cars(registration_number)
);

CREATE TABLE cars_locations (
	car VARCHAR(8) NOT NULL UNIQUE,
    latitude DOUBLE NOT NULL,
    longitude DOUBLE NOT NULL,
    
    FOREIGN KEY(car) REFERENCES cars(registration_number)
);

CREATE TABLE travels (
	user_id INTEGER NOT NULL,
    car VARCHAR(8) NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    
    FOREIGN KEY(user_id) REFERENCES users(id),
    FOREIGN KEY(car) REFERENCES cars(registration_number)
);