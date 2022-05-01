package ru.otus.springwork05.relation;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import ru.otus.springwork05.model.Book;
import ru.otus.springwork05.model.KindBook;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookResultExtractor implements ResultSetExtractor<Map<Long, Book>> {

    @Override
    public Map<Long, Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Long, Book> books = new HashMap<>();
        while (rs.next()) {
            long id = rs.getLong("id");
            Book book = books.get(id);
            if (book == null) {
                book = new Book(id, rs.getString("name"), new ArrayList<>(), new ArrayList<>());
                books.put(book.getId(), book);
            }
            book.getKindBooks().add(new KindBook(rs.getLong("kindId"), rs.getString("kindName")));
        }
        return books;
    }
}
