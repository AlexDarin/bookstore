DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS books_authors;
DROP SEQUENCE IF EXISTS book_id_seq;
DROP SEQUENCE IF EXISTS author_id_seq;

CREATE TABLE books (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    isbn VARCHAR(255) NOT NULL
);

CREATE TABLE authors (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    amount_of_books INT NOT NULL
);

CREATE TABLE books_authors (
    book_id INT NOT NULL ,
    author_id INT NOT NULL
);

INSERT INTO books (title, isbn) VALUES
    ('Бедные люди', '978-5-9268-2712-2'),
    ('Бесы', '978-5-17-090413-6');

INSERT INTO authors (first_name, last_name, amount_of_books) VALUES
    ('Фёдор', 'Достоевский', 2);

INSERT INTO books_authors (book_id, author_id) VALUES
    (1, 1),
    (2, 1);

CREATE SEQUENCE if not exists book_id_seq MINVALUE 3 START WITH 3;

CREATE SEQUENCE if not exists author_id_seq MINVALUE 2 START WITH 2;