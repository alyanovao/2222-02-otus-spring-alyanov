package ru.otus.springwork09.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springwork09.model.Author;

public interface AuthorDao extends JpaRepository<Author, Long> {
}
