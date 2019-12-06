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
    userID INTEGER NOT NULL,
    title VARCHAR(20),
    itemDescription VARCHAR(100),
    postDate DATE, 
    price DECIMAL(9, 2),
    categoryName VARCHAR(30),
    PRIMARY KEY(itemID),
    FOREIGN KEY(userID) REFERENCES users(userID)
);
set foreign_key_checks = 1;
drop table items;
DELETE FROM items;
select * from items;
select itemID,title,itemDescription,postDate,price,categoryName from items WHERE categoryName = "electronics";

insert into items values(
1, 2, "smartphone", "is aiight", "2019-12-05", 100.00, "electronics");

CREATE TABLE reviews (
	reviewID INTEGER NOT NULL auto_increment,
    score VARCHAR(10),
    remark VARCHAR(75),
    itemID integer not null,
    userID integer not null,
    reviewDate DATE, 
    PRIMARY KEY (reviewID),
    FOREIGN KEY (userID) REFERENCES users(userID),
    FOREIGN KEY (itemID) REFERENCES items(itemID)
);
drop table reviews;
DELETE FROM reviews;
select * from reviews;
