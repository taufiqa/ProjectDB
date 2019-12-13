use projectdb;
create database projectdb;
CREATE TABLE users (
	userID INTEGER NOT NULL auto_increment,
    firstName VARCHAR(25),
    lastName VARCHAR(25),
    password VARCHAR(8),
    email VARCHAR(25),
    gender CHAR(2),
    age INTEGER,
    PRIMARY KEY(userID,email),
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
    userEmail VARCHAR(25),
    title VARCHAR(20),
    itemDescription VARCHAR(100),
    postDate DATE, 
    price DECIMAL(9, 2),
    categoryName VARCHAR(30),
    PRIMARY KEY(itemID)
);
set foreign_key_checks = 1;
drop table items;
DELETE userID FROM items;
select * from items;
select itemID,title,itemDescription,postDate,price,categoryName from items WHERE categoryName = "electronics";

insert into items values(
4, "nuhamal@gmail.com", "talking book", "it's a book that talks", "2019-12-08", 500.00, "electronics,books");

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

CREATE TABLE favUsers (
	userID int NOT NULL, 
    favUserID int NOT NULL,
    userEmail varchar(25),
    favUserEmail varchar(25),
    PRIMARY KEY(userID,favUserID), 
    FOREIGN KEY(userID) REFERENCES users(userID),
    FOREIGN KEY(favUserID) REFERENCES users(userID)
);
insert into favUsers values(2, 3);
select * from users;
select * from favUsers;

CREATE TABLE favItems (
	itemID int NOT NULL, 
    userID int NOT NULL, 
    PRIMARY KEY(itemID,userID), 
    FOREIGN KEY(itemID) REFERENCES Items(itemID), 
    FOREIGN KEY(userID) REFERENCES Users(userID)
);
drop table favItems;
insert into favItems values(2, 2, "smartphone");
select * from favItems;