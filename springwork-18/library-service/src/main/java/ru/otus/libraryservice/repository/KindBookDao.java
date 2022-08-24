package ru.otus.libraryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.libraryservice.model.KindBook;

public interface KindBookDao extends JpaRepository<KindBook, Long> {
}
