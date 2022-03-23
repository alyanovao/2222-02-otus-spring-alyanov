package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.dao.PersonAnswerDao;
import ru.otus.domain.PersonAnswer;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CheckServiceImpl implements CheckService {

    private final PersonAnswerDao dao;

    @Override
    public int examine(List<PersonAnswer> answers) {
        return dao.examine(answers);
    }

    @Override
    public int checkResult(int count) {
        return dao.checkResult(count);
    }
}
