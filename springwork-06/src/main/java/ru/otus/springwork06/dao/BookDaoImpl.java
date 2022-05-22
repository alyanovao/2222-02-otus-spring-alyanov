package ru.otus.springwork06.dao;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springwork06.model.Book;
import ru.otus.springwork06.model.Commentary;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    EntityManager em;

    @Override
    public Optional<Book> findById(long id) {
        Book book = em.createQuery("select b from Book b where b.id = :id", Book.class)
                .setParameter("id", id).getSingleResult();
        return Optional.ofNullable(book);
    }

    @Override
    public List<Book> findAll() {
        return em.createQuery("select b" +
                " from Book b").getResultList();
    }

    @Override
    public Book save(Book book) {
        em.merge(book);
        return book;
    }

    @Override
    public Book merge(Book book) {
        return em.merge(book);
    }

    @Override
    public void deleteBook(Book book) {
        Book book2context = em.merge(book);
        em.remove(book2context);
    }
}
