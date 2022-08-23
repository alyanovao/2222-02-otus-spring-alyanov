package ru.otus.libraryserviceslave.repository;

import org.springframework.data.jpa.domain.Specification;
import ru.otus.libraryserviceslave.model.Book;

public class BookSpecification {
    public static Specification<Book> firstNameLike(String firstName) {
        if (firstName == null || firstName.equals("")) {
            return null;
        } else {
            return (root, query, cb) -> {
                query.distinct(true);
                return cb.like(root.join("authors").get("firstName"), "%" + firstName + "%");
            };
        }
    }

    public static Specification<Book> kindLike(String kindName) {
        if (kindName == null || kindName.equals("")) {
            return null;
        } else {
            return (root, query, cb) -> {
                query.distinct(true);
                return cb.like(root.join("kind").get("name"), "%" + kindName + "%");
            };
        }
    }
}
