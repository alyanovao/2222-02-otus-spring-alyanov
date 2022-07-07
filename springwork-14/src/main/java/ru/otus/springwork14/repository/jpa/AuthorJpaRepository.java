package ru.otus.springwork14.repository.jpa;

import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.otus.springwork14.model.Author;

public interface AuthorJpaRepository extends PagingAndSortingRepository<Author, Long> {
}
