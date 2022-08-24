package ru.otus.libraryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.libraryservice.model.Commentary;

public interface CommentaryDao extends JpaRepository<Commentary, Long> {
}
