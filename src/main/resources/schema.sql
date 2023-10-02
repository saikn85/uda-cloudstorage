CREATE TABLE IF NOT EXISTS USERS (
  userId INT PRIMARY KEY auto_increment,
  username VARCHAR(20) UNIQUE NOT NULL,
  salt VARCHAR,
  password VARCHAR,
  firstName VARCHAR(20),
  lastName VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS NOTES (
    noteId INT PRIMARY KEY auto_increment,
    userId INT NOT NULL,
    noteTitle VARCHAR(20),
    noteDescription VARCHAR (1000),
    foreign key (userId) references USERS(userId)
);

CREATE TABLE IF NOT EXISTS FILES (
    fileId INT PRIMARY KEY auto_increment,
    userId INT NOT NULL,
    fileName VARCHAR,
    contentType VARCHAR,
    fileSize VARCHAR,
    fileData BLOB,
    foreign key (userId) references USERS(userId)
);

CREATE TABLE IF NOT EXISTS CREDENTIALS (
    credentialId INT PRIMARY KEY auto_increment,
    userId INT NOT NULL,
    url VARCHAR(100),
    userName VARCHAR (30),
    key VARCHAR,
    password VARCHAR,
    foreign key (userId) references USERS(userId)
);
