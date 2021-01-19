-- Daniel Hinbest
-- WEBD 4201
-- 19-January-2021

DROP TABLE IF EXISTS students;

CREATE TABLE students (
	id BIGINT REFERENCES users(id),
	program_code CHAR(5) NOT NULL,
	program_description VARCHAR(20) NOT NULL,
	year INT NOT NULL
);

SELECT * FROM students;