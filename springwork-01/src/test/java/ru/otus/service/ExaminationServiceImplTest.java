package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.dao.ExaminationDao;
import ru.otus.domain.Examination;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ExaminationServiceImplTest {

    @Mock
    private ExaminationDao examinationDao;

    private ExaminationService examinationService;

    @BeforeEach
    void setUp() {
        examinationService = new ExaminationServiceImpl(examinationDao);
    }

    @Test
    void getExaminationById() {
        given(examinationDao.getExaminationById(eq(1)))
                .willReturn(new Examination(1, "Test", new ArrayList<>(), new ArrayList<>()));

        assertThat(examinationService.getExaminationById(1))
                .isNotNull();
    }
}
