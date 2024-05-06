--drop table if exist
DROP TABLE IF EXISTS users;

--create table
CREATE TABLE users(
    id SERIAL PRIMARY KEY NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    created_date TIMESTAMP NOT NULL,
    updated_date TIMESTAMP NULL
)
WITH(
    OIDS=FALSE
);

--select data from users
SELECT * FROM users;