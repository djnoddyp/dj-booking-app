#DROP TABLE IF EXISTS Bookings;
#DROP TABLE IF EXISTS Artists;

CREATE TABLE IF NOT EXISTS Artists
(
  ID   INT NOT NULL AUTO_INCREMENT,
  Name VARCHAR(20),
  Fee  DOUBLE,
  PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS Bookings
(
  ID INT NOT NULL AUTO_INCREMENT,
  Name VARCHAR(20),
  Date DATE,
  StartTime TIME,
  FinishTime TIME,
  ArtistID INT,
  PRIMARY KEY (ID),
  FOREIGN KEY (ArtistID) REFERENCES Artists(ID)
);

CREATE TABLE IF NOT EXISTS users
(
  username VARCHAR(30),
  password VARCHAR(30),
  password_salt VARCHAR(30),
  PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS user_roles
(
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(30),
  role_name VARCHAR(30),
  PRIMARY KEY (id),
  FOREIGN KEY (username) REFERENCES users(username)
);

CREATE TABLE IF NOT EXISTS roles_permissions
(
  id INT NOT NULL AUTO_INCREMENT,
  role_name VARCHAR(30),
  permission VARCHAR(30),
  PRIMARY KEY (id)
);