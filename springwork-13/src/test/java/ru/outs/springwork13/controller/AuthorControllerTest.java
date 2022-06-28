package ru.outs.springwork13.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.outs.springwork13.repository.UserDao;
import ru.outs.springwork13.service.AuthorService;
import ru.outs.springwork13.service.CustomAuthenticationProvider;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AuthorController.class)
@Import({CustomAuthenticationProvider.class, UserDao.class})
class AuthorControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mock;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private UserDao userDao;

    @MockBean
    private PasswordEncoder encoder;

    @BeforeEach
    void setUp() {
        mock = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser(
            username = "admin",
            authorities = "ROLE_ADMIN"
    )

    @Test
    void getAuthor() throws Exception {
        mock.perform(MockMvcRequestBuilders.get("/author"))
                .andExpect(status().isOk());
    }

    @Test
    void authorEditPage() throws Exception {
        mock.perform(MockMvcRequestBuilders.get("/authorEdit?id=1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void authorEdit() throws Exception {
        mock.perform(MockMvcRequestBuilders.post("/authorEdit?id=1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void saveAuthor() throws Exception {
        mock.perform(MockMvcRequestBuilders.post("/authorEdit?id=0")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());
    }
}