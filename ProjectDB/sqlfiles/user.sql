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
	"Root", "User", "pass1234", "root", "M", 45
);
drop table users;

select * from users;
