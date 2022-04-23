package ru.otus.springwork05.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.springwork05.exception.BookNotFoundException;
import ru.otus.springwork05.model.Author;
import ru.otus.springwork05.model.Book;
import ru.otus.springwork05.model.BookView;
import ru.otus.springwork05.model.KindBook;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookViewDaoImpl implements BookViewDao {

    private final AuthorDao authorDao;
    private final KindBookDao kindDao;
    private final BookDao bookDao;

    @Override
    public BookView getBookById(long id) {
        Book book;
        try {
            book = bookDao.getById(id);
        }
        catch (Exception e) {
            throw new BookNotFoundException("Book not found with id");
        }
        Author author = authorDao.getById(book.getAuthorId());
        KindBook kind = kindDao.getById(book.getKindId());
        return new BookView(book.getId(), book.getName(), getAuthorName(author), kind.getName());
    }

    @Override
    public BookView getBookByName(String name) {
        Book book;
        try {
            book = bookDao.getByName(name);
        }
        catch(Exception e) {
            throw new BookNotFoundException("Book not found with name");
        }
        KindBook kind = kindDao.getById(book.getId());
        return new BookView(book.getId(), book.getName(), getAuthorName(authorDao.getById(book.getAuthorId())), kind.getName());
    }

    @Override
    public List<BookView> getAllBooks() {
        List<Book> books = bookDao.getAll();
        List<BookView> booksView = new ArrayList<>();
        for (Book book: books) {
            booksView.add(new BookView(book.getId(), book.getName(), getAuthorName(authorDao.getById(book.getAuthorId())),
                    (kindDao.getById(book.getKindId())).getName()));
        }return booksView;
    }

    private String getAuthorName(Author author) {
        StringBuilder sb = new StringBuilder();
        sb.append(author.getFirstName()).append(" ");
        sb.append(author.getLastName()).append(" ");
        sb.append(author.getPartonymic());
        return sb.toString();
    }
}
