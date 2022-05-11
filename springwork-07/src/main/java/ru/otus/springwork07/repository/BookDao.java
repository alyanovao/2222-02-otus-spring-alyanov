package ru.otus.springwork07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.otus.springwork07.model.Book;

public interface BookDao extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
}
