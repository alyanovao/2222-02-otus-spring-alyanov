package ru.outs.springwork13.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.outs.springwork13.model.Author;

public interface AuthorDao extends JpaRepository<Author, Long> {
}
