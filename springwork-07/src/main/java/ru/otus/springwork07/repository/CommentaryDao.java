package ru.otus.springwork07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springwork07.model.Commentary;

public interface CommentaryDao extends JpaRepository<Commentary, Long> {
}
