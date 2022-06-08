package ru.otus.springwork09.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.springwork09.model.KindBook;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class KindBookServiceTest {
    public static final KindBook kindBook_1 = new KindBook("kindBook");

    @Mock
    private KindBookService kindBookService;

    @Test
    void findById() {
        kindBookService.findById(1);
        verify(kindBookService, times(1)).findById(1);
    }

    @Test
    void findAll() {
        kindBookService.findAll();
        verify(kindBookService, times(1)).findAll();
    }

    @Test
    void save() {
        kindBookService.save(kindBook_1);
        verify(kindBookService, times(1)).save(kindBook_1);
    }
}