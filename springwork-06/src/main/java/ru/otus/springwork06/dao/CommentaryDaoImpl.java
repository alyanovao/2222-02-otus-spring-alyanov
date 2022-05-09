package ru.otus.springwork06.dao;

import org.springframework.stereotype.Repository;
import ru.otus.springwork06.model.Commentary;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CommentaryDaoImpl implements CommentaryDao {

    @PersistenceContext
    EntityManager em;

    @Override
    public Commentary getById(long id) {
        return em.find(Commentary.class, id);
    }

    @Override
    public List<Commentary> getAll() {
        return em.createQuery("select c from Commentary c").getResultList();
    }

    @Override
    public Commentary save(Commentary commentary) {
       em.persist(commentary);
       return commentary;
    }

    @Override
    public Commentary update(Commentary commentary) {
        return em.merge(commentary);
    }

    @Override
    public void delete(Commentary commentary) {
        em.remove(commentary);
        em.clear();
    }
}
