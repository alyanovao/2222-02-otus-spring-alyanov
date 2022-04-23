create table books(
                        id bigserial,
                        name varchar(50),
                        authorId bigint references authors(id) ON DELETE CASCADE,
                        kindId bigint references kind(id) ON DELETE CASCADE
)