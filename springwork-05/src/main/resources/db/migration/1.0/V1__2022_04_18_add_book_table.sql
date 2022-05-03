create table authors(
    id bigserial,
    firstName varchar(50),
    lastName varchar(50),
    patronymic varchar(50),
    primary key (id)
);

create table kind(
    id bigserial,
    name varchar(50),
    primary key (id)
);

create table books(
    id bigserial,
    name varchar(50),
    kindId bigint references kind(id) ON DELETE CASCADE,
    primary key (id)
);

create table book_authors (
    bookId bigint references books(id) on delete cascade,
    authorId bigint references authors(id) on delete cascade,
    primary key (bookId, authorId)
);