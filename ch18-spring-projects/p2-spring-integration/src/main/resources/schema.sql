DROP TABLE singer IF EXISTS;

CREATE TABLE singer  (
	id INTEGER NOT NULL AUTO_INCREMENT,
	first_name VARCHAR(20),
	last_name VARCHAR(20),
	song VARCHAR(100),
	PRIMARY KEY (id)
);