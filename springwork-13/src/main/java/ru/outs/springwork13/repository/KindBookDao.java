package ru.outs.springwork13.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.outs.springwork13.model.KindBook;

public interface KindBookDao extends JpaRepository<KindBook, Long> {
}
