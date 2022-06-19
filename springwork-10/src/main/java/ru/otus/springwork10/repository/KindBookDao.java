package ru.otus.springwork10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springwork10.model.KindBook;

public interface KindBookDao extends JpaRepository<KindBook, Long> {
}
