-- Daniel Hinbest
-- WEBD 4201
-- 19-January-2021

-- Drops the faculty table if it exists
DROP TABLE IF EXISTS faculty;

-- Creates a new faculty table
CREATE TABLE faculty (
	id BIGINT PRIMARY KEY REFERENCES users(id),
	school_code CHAR(5) NOT NULL,
	school_description VARCHAR(70) NOT NULL,
	office VARCHAR(8) NOT NULL,
	extension INT NOT NULL
);

-- Inserts 3 faculty members with the faculty IDs matching the users table
INSERT INTO faculty VALUES (
	100743832,
	'BITM',
	'School of Business, IT, & Management',
	'C-315',
	2710
);

INSERT INTO faculty VALUES (
	100736293,
	'BITM',
	'School of Business, IT, & Management',
	'C-122',
	7329
);

INSERT INTO faculty VALUES (
	100329873,
	'SET',
	'School of Science & Engineering Technology',
	'C-108',
	2302
);

SELECT * FROM faculty;