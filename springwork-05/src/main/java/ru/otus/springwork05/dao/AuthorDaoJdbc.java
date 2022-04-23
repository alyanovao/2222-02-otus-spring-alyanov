package ru.otus.springwork05.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.springwork05.model.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public Author getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject("select id, firstName, lastName, patronymic from authors where id = :id",
                params, new AuthorMap());
    }

    @Override
    public void insert(Author author) {
        namedParameterJdbcOperations.update("insert into authors(id, firstName, lastName, patronymic) values(:id, :firstName, :lastName, :patronymic)",
                Map.of("id", author.getId(),
                        "firstName", author.getFirstName(),
                        "lastName", author.getLastName(),
                        "patronymic", author.getPartonymic()));
    }

    @Override
    public List<Author> getAll() {
        return namedParameterJdbcOperations.query("select id, firstName, lastName, patronymic from authors", new AuthorMap());
    }

    @Override
    public long getEmptyId() {
        return namedParameterJdbcOperations.getJdbcOperations().queryForObject(
                "with s as (" +
                        "select id " +
                        "from authors" +
                        ")" +
                        "select id + 1 " +
                        "from authors " +
                        "where (select 1 from s where authors.id + 1 = s.id) is null " +
                        "order by id " +
                        "limit 1", Long.class);
    }

    private static class AuthorMap implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            String patronymic = rs.getString("patronymic");
            return new Author(id, firstName, lastName, patronymic);
        }
    }
}
