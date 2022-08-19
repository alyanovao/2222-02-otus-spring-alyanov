package ru.otus.libraryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.libraryservice.model.Author;

public interface AuthorDao extends JpaRepository<Author, Long> {
}
