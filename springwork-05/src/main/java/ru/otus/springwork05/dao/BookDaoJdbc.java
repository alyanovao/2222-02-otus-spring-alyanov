package ru.otus.springwork05.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.springwork05.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject("select id, name, authorId, kindId from books where id = :id",
                params, new BookMap());
    }

    @Override
    public Book getByName(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);
        return namedParameterJdbcOperations.queryForObject("select id, name, authorId, kindId from books where name = :name",
                params, new BookMap());
    }

    @Override
    public void insert(Book book) {
        namedParameterJdbcOperations.update("insert into books(id, name, authorId, kindId) values (:id, :name, :authorId, :kindId)",
                Map.of("id", book.getId(),
                       "name", book.getName(),
                       "authorId", book.getAuthorId(),
                       "kindId", book.getKindId()));
    }

    @Override
    public void update(Book book) {
        namedParameterJdbcOperations.update("update books set name = :name, authorId = :authorId, kindId = :kindId where id = :id",
                Map.of("id", book.getId(),
                        "name", book.getName(),
                        "authorId", book.getAuthorId(),
                        "kindId", book.getKindId()));
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
        return namedParameterJdbcOperations.query("select id, name, authorId, kindId from books", new BookMap());
    }

    private static class BookMap implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            long authorId = rs.getLong("authorId");
            long kindId = rs.getLong("kindId");
            return new Book(id, name, authorId, kindId);
        }
    }
}