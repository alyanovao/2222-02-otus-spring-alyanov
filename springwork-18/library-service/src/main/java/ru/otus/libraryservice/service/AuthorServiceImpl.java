package ru.otus.libraryservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.libraryservice.model.Author;
import ru.otus.libraryservice.repository.AuthorDao;

import java.util.List;
import java.util.Optional;

@Slf4j
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
        log.info("Start service");
        return authorDao.findAll();
    }

    @Override
    @Transactional
    public Author save(Author author) {
        return authorDao.save(author);
    }
}
