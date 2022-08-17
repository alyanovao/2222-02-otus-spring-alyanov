package ru.otus.springwork17.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springwork17.model.Commentary;

public interface CommentaryDao extends JpaRepository<Commentary, Long> {
}
