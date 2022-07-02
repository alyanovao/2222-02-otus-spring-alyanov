package ru.outs.springwork13.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.outs.springwork13.model.Commentary;

public interface CommentaryDao extends JpaRepository<Commentary, Long> {
}
