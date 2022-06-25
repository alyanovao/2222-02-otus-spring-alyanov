package ru.otus.springwork12.controller;

import com.sun.xml.bind.v2.runtime.reflect.Lister;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.otus.springwork12.repository.UserDao;
import ru.otus.springwork12.service.AuthorService;
import ru.otus.springwork12.service.BookService;
import ru.otus.springwork12.service.CustomAuthenticationProvider;
import ru.otus.springwork12.service.KindBookService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(BookController.class)
@Import({CustomAuthenticationProvider.class, UserDao.class})
class BookControllerTest {

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

//    @WithMockUser(
//            username = "user",
//            authorities = "USER"
//    )

    @Test
    void getBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/book"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void editBookPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bookEdit"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void editBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/bookEdit"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void deleteBookPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bookDelete"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void deleteBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/book"))
                .andExpect(status().is4xxClientError());
    }
}