package ru.otus.springwork16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.otus.springwork16.model.Book;

public interface BookDao extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
}
