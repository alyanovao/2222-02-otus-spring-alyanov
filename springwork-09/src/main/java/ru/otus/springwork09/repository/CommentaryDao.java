package ru.otus.springwork09.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springwork09.model.Commentary;

public interface CommentaryDao extends JpaRepository<Commentary, Long> {
}
