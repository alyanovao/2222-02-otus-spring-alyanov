package ru.otus.springwork12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springwork12.model.Commentary;

public interface CommentaryDao extends JpaRepository<Commentary, Long> {
}
