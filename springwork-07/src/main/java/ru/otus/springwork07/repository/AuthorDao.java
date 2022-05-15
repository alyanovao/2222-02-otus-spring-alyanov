package ru.otus.springwork07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springwork07.model.Author;

public interface AuthorDao extends JpaRepository<Author, Long> {
}
