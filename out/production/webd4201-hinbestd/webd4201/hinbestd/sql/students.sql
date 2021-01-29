-- Daniel Hinbest
-- WEBD 4201
-- 19-January-2021

-- Drops the students table if it exists
DROP TABLE IF EXISTS students;

-- Creates a students table
CREATE TABLE students (
	id BIGINT PRIMARY KEY REFERENCES users(id),
	program_code CHAR(5) NOT NULL,
	program_description VARCHAR(70) NOT NULL,
	year INT NOT NULL
);

-- Inserts 3 students with the student IDs matching the users table
INSERT INTO students VALUES (
	100717231,
	'CPA',
	'Computer Programmer Analyst',
	2
);

INSERT INTO students VALUES (
	100111111,
	'CSTY',
	'Computer Systems Technology',
	3
);

INSERT INTO students VALUES (
	100843928,
	'EMTY',
	'Electromechanical Engineering Technology',
	1
);

SELECT * FROM students;