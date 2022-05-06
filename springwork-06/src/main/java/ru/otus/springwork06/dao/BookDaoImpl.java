package ru.otus.springwork06.dao;

import org.springframework.stereotype.Repository;
import ru.otus.springwork06.model.Book;

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
        return Optional.ofNullable(em.createQuery("select b from Book b left join fetch b.kind where b.id = :id", Book.class)
                .setParameter("id", id).getSingleResult());
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
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
