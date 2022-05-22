package ru.otus.springwork08.repository;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import ru.otus.springwork08.model.Book;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@NoArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Book getById(String id) {
        return mongoTemplate.findById(id, Book.class, "book");
    }

    @Override
    public List<Book> getAllByFirstNameAndKind(String firstName, String kind) {
        Criteria booksCriteria = new Criteria();
        booksCriteria.orOperator(Criteria.where("authors.firstName").regex(firstName),
                Criteria.where("kind.name").regex(kind));
        Aggregation aggregation = newAggregation(match(booksCriteria));
        return mongoTemplate.aggregate(aggregation, Book.class, Book.class).getMappedResults();
    }
}