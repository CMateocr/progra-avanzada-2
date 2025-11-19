-- Creación de la tabla 'author'
CREATE TABLE author (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    version INTEGER
);

-- Creación de la tabla 'book'
CREATE TABLE book (
    isbn VARCHAR(255) PRIMARY KEY,
    price DOUBLE PRECISION,
    title VARCHAR(255),
    version INTEGER
);

-- Creación de la tabla 'customer'
CREATE TABLE customer (
    id BIGINT PRIMARY KEY,
    email VARCHAR(255),
    name VARCHAR(255),
    version INTEGER
);


-- Creación de la tabla 'inventory'
-- Depende de 'book'
CREATE TABLE inventory (
    book_isbn_isbn VARCHAR(255) PRIMARY KEY,
    sold INTEGER,
    supplied INTEGER,
    version INTEGER,
    CONSTRAINT fk_inventory_book FOREIGN KEY (book_isbn_isbn) REFERENCES book (isbn)
);

-- Creación de la tabla 'purchaseorder'
-- Depende de 'customer'
CREATE TABLE purchaseorder (
    id BIGINT PRIMARY KEY,
    deliveredon TIMESTAMP,
    placedon TIMESTAMP,
    status INTEGER,
    total INTEGER,
    customer_id BIGINT,
    CONSTRAINT fk_order_customer FOREIGN KEY (customer_id) REFERENCES customer (id)
);

-- Creación de la tabla 'book_author' (Tabla de unión)
-- Depende de 'book' y 'author'
CREATE TABLE book_author (
    books_isbn_isbn VARCHAR(255),
    authors_id BIGINT,
    PRIMARY KEY (books_isbn_isbn, authors_id),
    CONSTRAINT fk_bookauthor_book FOREIGN KEY (books_isbn_isbn) REFERENCES book (isbn),
    CONSTRAINT fk_bookauthor_author FOREIGN KEY (authors_id) REFERENCES author (id)
);

-- Creación de la tabla 'lineitem'
-- Depende de 'purchaseorder' y 'book'
CREATE TABLE lineitem (
    order_id BIGINT,
    idx INTEGER,
    quantity INTEGER,
    book_isbn_isbn VARCHAR(255),
    PRIMARY KEY (order_id, idx),
    CONSTRAINT fk_lineitem_order FOREIGN KEY (order_id) REFERENCES purchaseorder (id),
    CONSTRAINT fk_lineitem_book FOREIGN KEY (book_isbn_isbn) REFERENCES book (isbn)
);