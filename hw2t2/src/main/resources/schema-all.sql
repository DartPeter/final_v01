DROP TABLE user IF EXISTS;

CREATE TABLE user (
	user_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
	name VARCHAR(20),
	email VARCHAR(20),
	balance INT
);

INSERT INTO user(name, email, balance) VALUES 
('vasa', 'vasa@mail.com', 9),
('petya', 'petya@mail.com', 19),
('kolya', 'kolya@mail.com', 1),
('sasha', 'sasha@mail.com', 5),
('olena', 'olena@mail.com', 10),
('nikita', 'nikita@mail.com', 12),
('brucelee', 'brucelee@mail.com', 18),
('chucky', 'chucky@mail.com', 4);
