package ru.otus.springwork08.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springwork08.model.KindBook;
import ru.otus.springwork08.repository.KindBookRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KindBookServiceImpl implements KindBookService {

    private final KindBookRepository kindBookRepository;

    @Override
    public Optional<KindBook> findById(String id) {
        return Optional.ofNullable(kindBookRepository.getById(id));
    }

    @Override
    public List<KindBook> findAll() {
        return kindBookRepository.findAll();
    }

    @Override
    @Transactional
    public KindBook save(KindBook kind) {
        return kindBookRepository.save(kind);
    }
}
