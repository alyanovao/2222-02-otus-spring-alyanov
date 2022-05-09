package ru.otus.springwork06.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springwork06.dao.KindBookDao;
import ru.otus.springwork06.model.KindBook;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KindBookServiceImpl implements KindBookService {

    private final KindBookDao kindBookDao;

    @Override
    public Optional<KindBook> findById(long id) {
        return kindBookDao.findById(id);
    }

    @Override
    public List<KindBook> findAll() {
        return kindBookDao.findAll();
    }

    @Override
    @Transactional
    public KindBook save(KindBook kind) {
        return kindBookDao.save(kind);
    }
}
