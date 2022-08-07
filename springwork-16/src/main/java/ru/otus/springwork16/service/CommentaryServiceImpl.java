package ru.otus.springwork16.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springwork16.exception.ComponentException;
import ru.otus.springwork16.exception.RepositoryException;
import ru.otus.springwork16.model.Commentary;
import ru.otus.springwork16.repository.CommentaryDao;

import javax.persistence.PersistenceException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentaryServiceImpl implements CommentaryService {

    private final CommentaryDao commentaryDao;

    @Override
    public Commentary getById(long id) {
        return commentaryDao.getReferenceById(id);
    }

    @Override
    public List<Commentary> getAll() {
        return commentaryDao.findAll();
    }

    @Override
    @Transactional
    public Commentary save(Commentary commentary) {
        try {
            return commentaryDao.save(commentary);
        }
        catch (InvalidDataAccessApiUsageException e) {
            throw new RepositoryException(e);
        }
        catch (PersistenceException e) {
            throw new ComponentException(e);
        }
    }

    @Override
    @Transactional
    public Commentary update(Commentary commentary) {
        return commentaryDao.save(commentary);
    }

    @Override
    @Transactional
    public void delete(long id) {
        var commentary = commentaryDao.getReferenceById(id);
        commentaryDao.delete(commentary);
    }
}
