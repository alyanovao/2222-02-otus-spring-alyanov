package ru.otus.springwork08.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.springwork08.model.KindBook;

@RequiredArgsConstructor
public class KindBookRepositoryCustomImpl implements KindBookRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public KindBook getById(String id) {
        return mongoTemplate.findById(id, KindBook.class, "kindbook");
    }
}