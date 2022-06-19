package ru.otus.springwork10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springwork10.model.Author;

public interface AuthorDao extends JpaRepository<Author, Long> {
}
