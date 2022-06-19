package ru.otus.springwork10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springwork10.model.Commentary;

public interface CommentaryDao extends JpaRepository<Commentary, Long> {
}
