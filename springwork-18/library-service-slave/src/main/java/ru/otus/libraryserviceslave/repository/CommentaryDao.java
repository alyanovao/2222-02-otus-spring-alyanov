package ru.otus.libraryserviceslave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.libraryserviceslave.model.Commentary;

public interface CommentaryDao extends JpaRepository<Commentary, Long> {
}
