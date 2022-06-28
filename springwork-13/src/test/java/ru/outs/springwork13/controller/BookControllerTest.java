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
import ru.outs.springwork13.service.BookService;
import ru.outs.springwork13.service.CustomAuthenticationProvider;
import ru.outs.springwork13.service.KindBookService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(BookController.class)
@Import({CustomAuthenticationProvider.class, UserDao.class})
class BookControllerTest {

    @Autowired
    private WebApplicationContext webContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private KindBookService kindBookService;

    @MockBean
    private UserDao userDao;

    @MockBean
    private PasswordEncoder encoder;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webContext)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser(
            username = "user",
            authorities = "USER"
    )

    @Test
    void getBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/book"))
                .andExpect(status().isOk());
    }

    @Test
    void editBookPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bookEdit"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void editBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/bookEdit?id=1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void deleteBookPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bookDelete"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void deleteBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/book?id=1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());
    }
}