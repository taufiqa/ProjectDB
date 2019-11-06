CREATE DATABASE onlineStore;

USE onlineStore;

CREATE TABLE items (
	itemID INTEGER NOT NULL auto_increment,
    title VARCHAR(20),
    itemDescription VARCHAR(100),
    postDate DATE,
    price DECIMAL(5, 2),
    PRIMARY KEY(itemID)
);

CREATE TABLE categories (
	categoryID INTEGER NOT NULL auto_increment,
    categoryName VARCHAR(15),
    itemID integer,
    PRIMARY KEY(categoryID, itemID),
    FOREIGN KEY (itemID) REFERENCES items(itemID), /*itemID here references itemID in items
													table, there will be multiple rows in table 
                                                    categories, one for each item*/
	CHECK (binary_checksum(categoryName) = binary_checksum(lower(categoryName)))
    /*check to ensure that category names are lowercase*/
);
CREATE TABLE users (
	userID INTEGER NOT NULL auto_increment,
    firstName VARCHAR(25),
    lastName VARCHAR(25),
    password VARCHAR(8),
    email VARCHAR(25),
    gender CHAR(2),
    age INTEGER,
    PRIMARY KEY(userID),
    CHECK (gender IN ('M', 'F'))
);
CREATE TABLE reviews (
	reviewID INTEGER NOT NULL auto_increment,
    score VARCHAR(10),
    remark VARCHAR(75),
    itemID integer,
    userID integer,
    reviewDate DATE,
    PRIMARY KEY (reviewID),
    CHECK (((SELECT COUNT (userID) FROM reviews) <= 5) WHILE reviewDate )
);
SELECT * FROM users;