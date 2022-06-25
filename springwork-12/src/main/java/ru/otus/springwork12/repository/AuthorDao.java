package ru.otus.springwork12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springwork12.model.Author;

public interface AuthorDao extends JpaRepository<Author, Long> {
}
