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
    title VARCHAR(20),
    itemDescription VARCHAR(100),
   /* postDate DATE, */
    price DECIMAL(5, 2),
    PRIMARY KEY(itemID)
);
set foreign_key_checks = 1;
drop table items;
DELETE FROM items;
select * from items;

CREATE TABLE categories (
	categoryID INTEGER NOT NULL auto_increment,
    categoryName VARCHAR(15),
    itemID integer,
    PRIMARY KEY(categoryID, itemID),
    FOREIGN KEY (itemID) REFERENCES items(itemID) /*itemID here references itemID in items
													table, there will be multiple rows in table 
                                                    categories, one for each item*/
	/*CHECK (binary_checksum(categoryName) = binary_checksum(lower(categoryName))) */
    /*check to ensure that category names are lowercase*/
);
drop table categories;
DELETE FROM categories;
select * from categories;

CREATE TABLE reviews (
	reviewID INTEGER NOT NULL auto_increment,
    score VARCHAR(10),
    remark VARCHAR(75),
    itemID integer,
    userID integer,
   /* reviewDate DATE, */
    PRIMARY KEY (reviewID)
    /*CHECK (((SELECT COUNT (userID) FROM reviews) <= 5))*/
);
drop table reviews;
DELETE FROM reviews;
select * from reviews;