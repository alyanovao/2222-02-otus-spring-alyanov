create table authors(
    id bigserial,
    first_name varchar(50),
    last_name varchar(50),
    patronymic varchar(50),
    primary key (id)
);

create table kind(
    id bigserial,
    kind_name varchar(50),
    primary key (id)
);

create table books(
    id bigserial,
    name varchar(50),
    primary key (id)
);

create table book_kinds
(
    book_id bigint references books (id) on delete cascade,
    kind_id bigint references kind (id) on delete cascade,
    primary key (book_id, kind_id)
);

create table book_authors (
    book_id bigint references books(id) on delete cascade,
    author_id bigint references authors(id) on delete cascade,
    primary key (author_id, book_id)
);

create table commentaries(
    id bigserial,
    commentary_value varchar(50),
    book_id bigint references books(id) ON DELETE CASCADE,
    primary key (id)
);