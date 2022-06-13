package ru.otus.springwork09.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springwork09.model.KindBook;

public interface KindBookDao extends JpaRepository<KindBook, Long> {
}
