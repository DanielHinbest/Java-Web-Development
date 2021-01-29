-- Daniel Hinbest
-- WEBD 4201
-- 19-January-2021

-- Creates an extension to pgcrypto for password hashing
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- Drops the users table if it exists
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS faculty;
DROP TABLE IF EXISTS users;

-- Creates a new users table
CREATE TABLE users (
	id BIGINT PRIMARY KEY,
	password VARCHAR(50) NOT NULL,
	first_name VARCHAR(35) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	email_address VARCHAR(255) NOT NULL,
	enabled BOOLEAN DEFAULT false,
	type CHAR(2) NOT NULL,
	enrol_date TIMESTAMP NOT NULL,
	last_access TIMESTAMP NOT NULL
);

-- Inserts records into the users table
INSERT INTO users VALUES (
	100717231,
	ENCODE(DIGEST('pass123', 'sha1'), 'hex'),
	'Daniel',
	'Hinbest',
	'daniel.hinbest@rogers.com',
	true,
	's',
	NOW(),
	NOW()
);

INSERT INTO users VALUES (
	100111111,
	ENCODE(DIGEST('password', 'sha1'), 'hex'),
	'Mike',
	'Jones',
	'mike.jones@dcmail.ca',
	true,
	's',
	'2020-01-21 1:36',
	'2020-01-01 1:37'
);

INSERT INTO users VALUES (
	100843928,
	ENCODE(DIGEST('abc123', 'sha1'), 'hex'),
	'Spencer',
	'O Reilly',
	'spencer.oreilly@dcmail.ca',
	true,
	's',
	'2020-01-21 1:43',
	'2020-01-21 1:45'
);

INSERT INTO users VALUES (
	100743832,
	ENCODE(DIGEST('faculty', 'sha1'), 'hex'),
	'Patrick',
	'McCartney',
	'patrick.mccartney@durhamcollege.ca',
	true,
	'f',
	'2020-01-21 1:49',
	'2020-01-21 1:50'
);

INSERT INTO users VALUES (
	100736293,
	ENCODE(DIGEST('12345678', 'sha1'), 'hex'),
	'Jasmine',
	'Frost',
	'jasmine.frost@durhamcollege.ca',
	true,
	'f',
	'2020-01-21 1:55',
	'2020-01-21 1:55'
);

INSERT INTO users VALUES (
	100329873,
	ENCODE(DIGEST('qwerty', 'sha1'), 'hex'),
	'Hayley',
	'Conroy',
	'hayley.conroy@durhamcollege.ca',
	true,
	'f',
	'2020-01-21 1:59',
	'2020-01-21 2:00'
);

SELECT * FROM users;