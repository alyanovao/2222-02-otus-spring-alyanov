package ru.otus.springwork17.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springwork17.model.Author;

public interface AuthorDao extends JpaRepository<Author, Long> {
}
