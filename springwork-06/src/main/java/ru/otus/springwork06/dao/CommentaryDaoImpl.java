package ru.otus.springwork06.dao;

import org.springframework.stereotype.Repository;
import ru.otus.springwork06.model.Commentary;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    public List<Commentary> getAllByBookId(long id) {
        List<Commentary> commentary = em.createQuery("select c from Commentary c where c.book_id = :id")
                .setParameter("id", id).getResultList();
        return commentary;
    }

    @Override
    public Commentary save(Commentary commentary) {
        return em.merge(commentary);
    }

    @Override
    public Commentary update(Commentary commentary) {
        em.createQuery("update Commentary c set c.value = :value where c.id = :id")
                .setParameter("value", commentary.getValue())
                .setParameter("id", commentary.getId())
                .executeUpdate();
        return commentary;
    }

    @Override
    public void delete(long id) {
        Query query = em.createQuery("delete from Commentary c where c.id = :id").setParameter("id", id);
        query.executeUpdate();
    }
}
