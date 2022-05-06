package ru.otus.springwork06.dao;

import org.springframework.stereotype.Repository;
import ru.otus.springwork06.model.KindBook;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class KindBookDaoImpl implements KindBookDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<KindBook> findById(long id) {
        return Optional.ofNullable(em.find(KindBook.class, id));
    }

    @Override
    public List<KindBook> findAll() {
        return em.createQuery("select e from KindBook e").getResultList();
    }

    @Override
    public KindBook save(KindBook kind) {
        if (kind.getId() == 0) {
            em.persist(kind);
            return kind;
        }
        else {
            return em.merge(kind);
        }
    }
}
