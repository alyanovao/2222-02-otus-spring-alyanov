package ru.otus.springwork08.repository;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.springwork08.model.Commentary;

@RequiredArgsConstructor
public class CommentaryRepositoryCustomImpl implements CommentaryRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public Commentary getById(String id) {
        return mongoTemplate.findById(id, Commentary.class, "commentary");
    }
}
