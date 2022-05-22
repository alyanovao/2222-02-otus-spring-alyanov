package ru.otus.springwork08.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springwork08.model.Author;
import ru.otus.springwork08.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public Optional<Author> findById(String id) {
        return Optional.ofNullable(authorRepository.getById(id));
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional
    public Author save(Author author) {
        return authorRepository.save(author);
    }
}
