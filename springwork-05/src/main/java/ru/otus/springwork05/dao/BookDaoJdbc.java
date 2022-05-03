package ru.otus.springwork05.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.springwork05.exception.BookNotFoundException;
import ru.otus.springwork05.model.Author;
import ru.otus.springwork05.model.Book;
import ru.otus.springwork05.relation.BookAuthorRelation;
import ru.otus.springwork05.relation.BookResultExtractor;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {

    private final AuthorDao authorDao;

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        Map<Long, Book> books = namedParameterJdbcOperations.query(
                "select b.id bookId, b.name bookName, authors.firstName, authors.lastName, authors.patronymic, kind.id kindId," +
                        "kind.name kindName " +
                        "from books b " +
                        "left join authors on b.id = authors.id " +
                        "left join kind on b.kindId = kind.id " +
                        "where b.id = :id", params, new BookResultExtractor());
        List<Author> authors = authorDao.getAll();
        mergeBook(books, authors, getAuthorRelationsById(id));
        return Objects.requireNonNull(books.get(id));
    }

    @Override
    public Book getByName(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);
        Map<Long, Book> books = namedParameterJdbcOperations.query(
                "select b.id bookId, b.name bookName, authors.firstName, authors.lastName, authors.patronymic, kind.id kindId," +
                        "kind.name kindName " +
                        "from books b " +
                        "left join authors on b.id = authors.id " +
                        "left join kind on b.kindId = kind.id " +
                        "where b.name = :name", params, new BookResultExtractor());
        List<Author> authors = authorDao.getAll();
        try {
            mergeBook(books, authors, getAuthorRelationsById(getBookFromMap(books).getId()));
            return Objects.requireNonNull(getBookFromMap(books));
        }
        catch (Exception e) {
            throw new BookNotFoundException("book not found by name");
        }
    }

    private Book getBookFromMap(Map<Long, Book> books) {
        return books.values().stream().findFirst().get();
    }

    @Override
    public void insert(Book book) {
        namedParameterJdbcOperations.update("insert into books(id, name, kindId) values (:id, :name, :kindId)",
                Map.of("id", book.getId(),
                        "name", book.getName(),
                        "kindId", book.getKindBooks().stream().findFirst().get().getId()));
        List<MapSqlParameterSource> params = new ArrayList<>();
        for (Author author: book.getAuthors()) {
            MapSqlParameterSource sqlParam = new MapSqlParameterSource();
            sqlParam.addValue("bookId", book.getId());
            sqlParam.addValue("authorId", author.getId());

            params.add(sqlParam);
        }
        namedParameterJdbcOperations.batchUpdate("insert into book_authors(bookId, authorId) values (:bookId, :authorId)",
                params.toArray(MapSqlParameterSource[]::new));

    }

    @Override
    public void update(Book book) {
        Map<String, Object> param = Collections.singletonMap("bookId", book.getId());
        namedParameterJdbcOperations.update("delete book_authors where bookId = :bookId", param);

        long id = book.getKindBooks().stream().findFirst().get().getId();
        namedParameterJdbcOperations.update("update books set name = :name, kindId = :kindId where id = :id",
                Map.of("id", book.getId(),
                        "name", book.getName(),
                        "kindId", id));

        List<MapSqlParameterSource> params = new ArrayList<>();
        for (Author author: book.getAuthors()) {
            MapSqlParameterSource sqlParam = new MapSqlParameterSource();
            sqlParam.addValue("bookId", book.getId());
            sqlParam.addValue("authorId", author.getId());

            params.add(sqlParam);
        }
        namedParameterJdbcOperations.batchUpdate("insert into book_authors(bookId, authorId) values (:bookId, :authorId)",
                params.toArray(MapSqlParameterSource[]::new));
    }

    @Override
    public long getEmptyId() {
        return namedParameterJdbcOperations.getJdbcOperations().queryForObject(
                "with s as (" +
                        "select id " +
                        "from books" +
                        ")" +
                        "select id + 1 " +
                        "from books " +
                        "where (select 1 from s where books.id + 1 = s.id) is null " +
                        "order by id " +
                        "limit 1", Long.class);
    }

    @Override
    public void delete(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update("delete from books where id = :id", params);
    }

    @Override
    public List<Book> getAll() {
        Map<Long, Book> books = namedParameterJdbcOperations.query(
                "select b.id bookId, b.name bookName, authors.firstName, authors.lastName, authors.patronymic, kind.id kindId," +
                        "kind.name kindName " +
                        "from books b " +
                        "left join authors on b.id = authors.id " +
                        "left join kind on b.kindId = kind.id", new BookResultExtractor());
        List<Author> authors = authorDao.getAll();
        mergeBook(books, authors, getAuthorRelations());
        return new ArrayList<>(Objects.requireNonNull(books).values());
    }

    private List<BookAuthorRelation> getAuthorRelations() {
        return namedParameterJdbcOperations.query("select bookId, authorId from book_authors", (rs, rowNum) ->
                new BookAuthorRelation(rs.getLong(1), rs.getLong(2)));
    }

    private List<BookAuthorRelation> getAuthorRelationsById(long id) {
        Map<String, Long> params = new HashMap<>();
        params.put("id", id);
        return namedParameterJdbcOperations.query("select bookId, authorId from book_authors where bookId = :id", params, (rs, rowNum) ->
                new BookAuthorRelation(rs.getLong(1), rs.getLong(2)));
    }

    private void mergeBook(Map<Long, Book> books,
                           List<Author> authors,
                           List<BookAuthorRelation> authorRelations) {
        Map<Long, Author> authorMap = authors.stream().collect(Collectors.toMap(Author::getId, Function.identity()));
        authorRelations.forEach(s -> {
            if (books.containsKey(s.getBookId()) && authorMap.containsKey(s.getAuthorId())) {
                books.get(s.getBookId()).getAuthors().add(authorMap.get(s.getAuthorId()));
            }
        });
    }
}