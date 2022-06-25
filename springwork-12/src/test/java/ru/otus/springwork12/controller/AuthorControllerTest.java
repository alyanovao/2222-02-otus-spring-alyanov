package ru.otus.springwork12.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.otus.springwork12.repository.UserDao;
import ru.otus.springwork12.service.AuthorService;
import ru.otus.springwork12.service.CustomAuthenticationProvider;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthorController.class)
@Import({CustomAuthenticationProvider.class, UserDao.class})
class AuthorControllerTest {

    @Autowired
    private MockMvc mock;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private UserDao userDao;

    @MockBean
    private PasswordEncoder encoder;

//    @WithMockUser(
//            username = "admin",
//            authorities = {"ROLE_ADMIN"}
//    )

    @Test
    void getAuthor() throws Exception {
        mock.perform(MockMvcRequestBuilders.get("/author"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void authorEditPage() throws Exception {
        mock.perform(MockMvcRequestBuilders.get("/authorEdit"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void authorEdit() throws Exception {
        mock.perform(MockMvcRequestBuilders.post("/authorEdit?id=1"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void saveAuthor() throws Exception {
        mock.perform(MockMvcRequestBuilders.post("/author"))
                .andExpect(status().is4xxClientError());
    }
}