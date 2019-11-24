CREATE DATABASE projectdb;

USE projectdb;

CREATE TABLE items (
	itemID INTEGER NOT NULL auto_increment,
    userEmail VARCHAR(25),
    title VARCHAR(20),
    itemDescription VARCHAR(100),
    postDate DATE,
    price DECIMAL(5, 2),
    PRIMARY KEY(itemID, title)
);
DELETE FROM categories;
SELECT * FROM items;
SELECT * FROM categories;
CREATE TABLE categories (
	categoryID INTEGER NOT NULL auto_increment,
    categoryName VARCHAR(15),
    itemTitle VARCHAR(20),
    PRIMARY KEY(categoryID, itemTitle)
    /*FOREIGN KEY (itemTitle) REFERENCES items(title) itemTitle here references title in items
													table, there will be multiple rows in table 
                                                    categories, one for each item*/
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
    PRIMARY KEY (reviewID)
);
SELECT * FROM users;
DELETE FROM items; DELETE FROM categories;