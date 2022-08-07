package ru.otus.springwork16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springwork16.model.Commentary;

public interface CommentaryDao extends JpaRepository<Commentary, Long> {
}
