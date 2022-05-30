package ru.otus.springwork08.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springwork08.exception.RepositoryException;
import ru.otus.springwork08.model.Commentary;
import ru.otus.springwork08.repository.CommentaryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentaryServiceImpl implements CommentaryService {

    private final CommentaryRepository commentaryRepository;

    @Override
    public Commentary getById(String id) {
        return commentaryRepository.getById(id);
    }

    @Override
    public List<Commentary> getAll() {
        return commentaryRepository.findAll();
    }

    @Override
    @Transactional
    public Commentary save(Commentary commentary) {
        try {
            return commentaryRepository.save(commentary);
        }
        catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    @Transactional
    public Commentary update(Commentary commentary) {
        return commentaryRepository.save(commentary);
    }

    @Override
    @Transactional
    public void delete(Commentary commentary) {
        commentaryRepository.delete(commentary);
    }
}
