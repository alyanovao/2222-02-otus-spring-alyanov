package ru.otus.springwork05.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.springwork05.model.KindBook;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class KindBookDaoJdbc implements KindBookDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    @Override
    public KindBook getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject("select id, name from kind where id = :id", params, new KindBookMap());
    }

    @Override
    public KindBook getByName(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);
        return namedParameterJdbcOperations.queryForObject("select id, name from kind where name = :name", params, new KindBookMap());
    }

    @Override
    public long getEmptyId() {
        return namedParameterJdbcOperations.getJdbcOperations().queryForObject(
                "with s as (" +
                        "select id " +
                        "from kind" +
                        ")" +
                        "select id + 1 " +
                        "from kind " +
                        "where (select 1 from s where kind.id + 1 = s.id) is null " +
                        "order by id " +
                        "limit 1", Long.class);
    }

    @Override
    public void insert(KindBook kind) {
        namedParameterJdbcOperations.update("insert into kind(id, name) values (:id, :name)",
                Map.of("id", kind.getId(),
                        "name", kind.getName()));
    }

    @Override
    public void delete(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update("delete from kind where id = :id", params);
    }

    @Override
    public List<KindBook> getAll() {
        return namedParameterJdbcOperations.query("select id, name from kind", new KindBookMap());
    }

    public static class KindBookMap implements RowMapper<KindBook> {

        @Override
        public KindBook mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            return new KindBook(id, name);
        }
    }
}
