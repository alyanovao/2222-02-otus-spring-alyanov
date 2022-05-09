package ru.otus.springwork06.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springwork06.dao.CommentaryDao;
import ru.otus.springwork06.exception.ComponentException;
import ru.otus.springwork06.exception.RepositoryException;
import ru.otus.springwork06.model.Commentary;

import javax.persistence.PersistenceException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentaryServiceImpl implements CommentaryService {

    private final CommentaryDao commentaryDao;

    @Override
    public Commentary getById(long id) {
        return commentaryDao.getById(id);
    }

    @Override
    public List<Commentary> getAll() {
        return commentaryDao.getAll();
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
        return commentaryDao.update(commentary);
    }

    @Override
    @Transactional
    public void delete(long id) {
        var commentary = commentaryDao.getById(id);
        commentaryDao.delete(commentary);
    }
}
