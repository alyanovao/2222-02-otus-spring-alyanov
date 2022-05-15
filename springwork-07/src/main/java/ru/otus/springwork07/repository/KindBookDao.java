package ru.otus.springwork07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springwork07.model.KindBook;

public interface KindBookDao extends JpaRepository<KindBook, Long> {
}
