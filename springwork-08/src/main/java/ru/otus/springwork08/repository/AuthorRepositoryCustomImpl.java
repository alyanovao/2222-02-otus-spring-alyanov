package ru.otus.springwork08.repository;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.otus.springwork08.model.Author;
import ru.otus.springwork08.model.Book;

@RequiredArgsConstructor
public class AuthorRepositoryCustomImpl implements AuthorRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    @Override
    public Author getById(String id) {
        val query = Query.query(Criteria.where("id").is(id));
        return mongoTemplate.find(query, Author.class, "author").get(0);
    }
}
