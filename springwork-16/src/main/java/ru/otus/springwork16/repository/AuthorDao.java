package ru.otus.springwork16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springwork16.model.Author;

public interface AuthorDao extends JpaRepository<Author, Long> {
}
