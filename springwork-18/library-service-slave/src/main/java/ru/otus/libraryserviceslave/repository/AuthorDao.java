package ru.otus.libraryserviceslave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.libraryserviceslave.model.Author;

public interface AuthorDao extends JpaRepository<Author, Long> {
}
