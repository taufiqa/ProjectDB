use projectdb;

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

insert into users values(
	0,"Root", "User", "pass1234", "root", "M", 45
);
set foreign_key_checks = 1;
drop table users;
DELETE FROM users;
select * from users;

CREATE TABLE items (
	itemID INTEGER NOT NULL auto_increment,
    userID INTEGER,
    title VARCHAR(20),
    itemDescription VARCHAR(100),
    postDate DATE, 
    price DECIMAL(9, 2),
    categoryName VARCHAR(30),
    PRIMARY KEY(itemID),
    FOREIGN KEY(userID) REFERENCES users(userID)
);
set foreign_key_checks = 0;
drop table items;
DELETE FROM items;
select * from items;

CREATE TABLE categories (
	categoryID INTEGER NOT NULL auto_increment,
    categoryName VARCHAR(15),
    itemTitle VARCHAR(20),
    PRIMARY KEY(categoryID, itemTitle)
);
drop table categories;
DELETE FROM categories;
select * from categories;

SELECT * FROM Items INNER JOIN categories ON Items.title = Categories.itemTitle;

CREATE TABLE reviews (
	reviewID INTEGER NOT NULL auto_increment,
    score VARCHAR(10),
    remark VARCHAR(75),
    itemID integer,
    userID integer,
    reviewDate DATE, 
    PRIMARY KEY (reviewID),
    FOREIGN KEY (userID) REFERENCES users(userID),
    FOREIGN KEY (itemID) REFERENCES items(itemID)
);
drop table reviews;
DELETE FROM reviews;
select * from reviews;