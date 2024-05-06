--drop table if exist
DROP TABLE IF EXISTS notes;

--create table
CREATE TABLE notes(
    id SERIAL PRIMARY KEY NOT NULL,
    user_id INT4,
    user_notes TEXT NOT NULL,
    created_date TIMESTAMP NOT NULL,
    updated_date TIMESTAMP NULL,
    CONSTRAINT fk_user_id
    FOREIGN KEY(user_id)
    REFERENCES users(id)
    ON UPDATE CASCADE ON DELETE CASCADE
)
WITH(
    OIDS=FALSE
);

--select data from notes
SELECT * FROM notes;