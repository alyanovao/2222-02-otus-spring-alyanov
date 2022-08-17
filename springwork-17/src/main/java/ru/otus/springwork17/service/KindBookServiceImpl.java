package ru.otus.springwork17.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springwork17.model.KindBook;
import ru.otus.springwork17.repository.KindBookDao;

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
