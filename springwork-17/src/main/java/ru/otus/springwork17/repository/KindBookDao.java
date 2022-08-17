package ru.otus.springwork17.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springwork17.model.KindBook;

public interface KindBookDao extends JpaRepository<KindBook, Long> {
}
