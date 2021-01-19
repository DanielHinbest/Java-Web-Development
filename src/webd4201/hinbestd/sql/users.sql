-- Daniel Hinbest
-- WEBD 4201
-- 19-January-2021

CREATE EXTENSION IF NOT EXISTS pgcrypto;

--DROP TABLE IF EXISTS users;

CREATE TABLE users (
	id BIGINT PRIMARY KEY,
	password VARCHAR(40) NOT NULL,
	first_name VARCHAR(35) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	email_address VARCHAR(255) NOT NULL,
	enabled BOOLEAN DEFAULT 'false',
	type CHAR(2) NOT NULL,
	enrol_date TIMESTAMP NOT NULL,
	last_access TIMESTAMP NOT NULL
);

INSERT INTO Users VALUES(
	100222222,
	ENCODE(DIGEST('password','sha1'), 'hex'),
	'Robert',
	'McReady',
	'bob.mcready@dcmail.ca',
	'true',
	's',
	'2019-1-21 08:25:43',
	'2019-1-21 11:11:11'
);

SELECT * FROM users;