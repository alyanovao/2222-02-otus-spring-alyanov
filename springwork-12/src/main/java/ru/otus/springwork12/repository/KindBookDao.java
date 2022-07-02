package ru.otus.springwork12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springwork12.model.KindBook;

public interface KindBookDao extends JpaRepository<KindBook, Long> {
}
