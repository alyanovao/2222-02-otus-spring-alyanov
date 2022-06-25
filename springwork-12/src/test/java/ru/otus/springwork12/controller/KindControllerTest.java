package ru.otus.springwork12.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.otus.springwork12.repository.UserDao;
import ru.otus.springwork12.service.CustomAuthenticationProvider;
import ru.otus.springwork12.service.KindBookService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(KindController.class)
@Import({CustomAuthenticationProvider.class})
class KindControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KindBookService kindBookService;

    @MockBean
    private UserDao userDao;

    @MockBean
    private PasswordEncoder encoder;

    @Test
    void getKindBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/kind"))
                .andExpect(status().is3xxRedirection());
    }
}