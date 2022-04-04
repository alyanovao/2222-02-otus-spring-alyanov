package ru.otus.springwork04.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.springwork04.dao.StudentAnswerHandler;
import ru.otus.springwork04.domain.StudentAnswer;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CheckServiceImpl implements CheckService {

    private final StudentAnswerHandler dao;

    @Override
    public int examine(List<StudentAnswer> answers) {
        return dao.examine(answers);
    }

    @Override
    public int checkResult(int count) {
        return dao.checkResult(count);
    }
}
