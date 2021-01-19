-- Daniel Hinbest
-- WEBD 4201
-- 19-January-2021

DROP TABLE IF EXISTS faculty;

CREATE TABLE faculty (
	id BIGINT REFERENCES users(id),
	school_code CHAR(5) NOT NULL,
	school_description VARCHAR(30) NOT NULL,
	office VARCHAR(8) NOT NULL,
	extension INT NOT NULL
);

SELECT * FROM faculty;