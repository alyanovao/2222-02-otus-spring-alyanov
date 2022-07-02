package ru.otus.springwork12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.otus.springwork12.model.Book;

public interface BookDao extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
}
