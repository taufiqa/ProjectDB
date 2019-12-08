CREATE DATABASE projectdb;

USE projectdb;

CREATE TABLE items (
	itemID INTEGER NOT NULL auto_increment,
    userEmail VARCHAR(25),
    title VARCHAR(20),
    itemDescription VARCHAR(100),
    postDate DATE,
    price DECIMAL(5, 2),
	categoryName VARCHAR(50),
    PRIMARY KEY(itemID)
);

DROP TABLE items;
DELETE FROM users;
SELECT * FROM items;

CREATE TABLE users (
	userID INTEGER NOT NULL auto_increment,
    firstName VARCHAR(25),
    lastName VARCHAR(25),
    password VARCHAR(8),
    email VARCHAR(25),
    gender CHAR(2),
    age INTEGER,
    PRIMARY KEY(userID, email),
    CHECK (gender IN ('M', 'F'))
);

DROP TABLE users;
CREATE TABLE reviews (
	reviewID INTEGER NOT NULL auto_increment,
    score VARCHAR(10),
    remark VARCHAR(75),
    itemID integer,
    userEmail VARCHAR(25),
    reviewDate DATE,
    PRIMARY KEY (reviewID)
);

SELECT * FROM users;
DELETE FROM items; DELETE FROM categories;