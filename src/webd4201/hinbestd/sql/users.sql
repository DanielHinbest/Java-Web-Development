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
	password VARCHAR(40) NOT NULL,
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
	ENCODE(DIGEST('pass1234', 'sha1'), 'hex'),
	'Daniel',
	'Hinbest',
	'daniel.hinbest@dcmail.ca',
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
	ENCODE(DIGEST('abcd1234', 'sha1'), 'hex'),
	'Vladimir',
	'Guerrero Jr',
	'vladimir.guerrerojr@dcmail.ca',
	true,
	's',
	'2020-01-21 1:43',
	'2020-01-21 1:45'
);

INSERT INTO users VALUES (
	100743832,
	ENCODE(DIGEST('faculty1', 'sha1'), 'hex'),
	'Bo',
	'Bichette',
	'bo.bichette@durhamcollege.ca',
	true,
	'f',
	'2020-01-21 1:49',
	'2020-01-21 1:50'
);

INSERT INTO users VALUES (
	100736293,
	ENCODE(DIGEST('12345678', 'sha1'), 'hex'),
	'George',
	'Springer',
	'george.springer@durhamcollege.ca',
	true,
	'f',
	'2020-01-21 1:55',
	'2020-01-21 1:55'
);

INSERT INTO users VALUES (
	100329873,
	ENCODE(DIGEST('qwertyui', 'sha1'), 'hex'),
	'Hyun-Jin',
	'Ryu',
	'hyunjin.ryu@durhamcollege.ca',
	true,
	'f',
	'2020-01-21 1:59',
	'2020-01-21 2:00'
);

SELECT * FROM users;