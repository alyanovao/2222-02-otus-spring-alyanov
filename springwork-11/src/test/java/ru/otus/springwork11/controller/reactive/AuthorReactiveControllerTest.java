package ru.otus.springwork11.controller.reactive;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.otus.springwork11.model.Author;

@SpringBootTest
class AuthorReactiveControllerTest {

    @Autowired
    @Qualifier("authorComponentRoutes")
    private RouterFunction<ServerResponse> route;

    private WebTestClient client;

    @BeforeEach
    void setUp() {
        this.client = WebTestClient.bindToRouterFunction(route).build();
    }

    @Test
    void getAuthors() {
        client.get()
                .uri("/api/authors")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void getAuthor() {
        client.get()
                .uri("/api/author/0")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void saveAuthor() {
        val author = new Author("lastName", "firstName", "patronymic");
        client.post()
                .uri("/api/author")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(author), Author.class)
                .exchange()
                .expectStatus()
                .isOk();
    }
}