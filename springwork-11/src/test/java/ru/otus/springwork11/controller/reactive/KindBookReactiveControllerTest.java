package ru.otus.springwork11.controller.reactive;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@SpringBootTest
public class KindBookReactiveControllerTest {

    @Autowired
    @Qualifier("kindComponentRoutes")
    private RouterFunction<ServerResponse> route;

    @Test
    public void getKindBook() {
        WebTestClient client = WebTestClient
                .bindToRouterFunction(route)
                .build();

        client.get()
                .uri("/api/kindBooks")
                .exchange()
                .expectStatus()
                .isOk();
}
}