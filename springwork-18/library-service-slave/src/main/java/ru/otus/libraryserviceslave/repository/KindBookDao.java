package ru.otus.libraryserviceslave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.libraryserviceslave.model.KindBook;

public interface KindBookDao extends JpaRepository<KindBook, Long> {
}
