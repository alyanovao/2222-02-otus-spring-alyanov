package ru.otus.springwork16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springwork16.model.KindBook;

public interface KindBookDao extends JpaRepository<KindBook, Long> {
}
