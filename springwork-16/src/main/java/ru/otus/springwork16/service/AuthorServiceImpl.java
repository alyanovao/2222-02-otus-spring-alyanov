package ru.otus.springwork16.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springwork16.model.Author;
import ru.otus.springwork16.repository.AuthorDao;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    public Optional<Author> findById(long id) {
        return authorDao.findById(id);
    }

    @Override
    public List<Author> findAll() {
        return authorDao.findAll();
    }

    @Override
    @Transactional
    public Author save(Author author) {
        return authorDao.save(author);
    }
}
