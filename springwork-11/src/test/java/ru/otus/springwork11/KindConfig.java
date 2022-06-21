package ru.otus.springwork11;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.otus.springwork11.model.KindBook;
import ru.otus.springwork11.repository.KindBookRepository;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class KindConfig {

    @Bean
    public RouterFunction<ServerResponse> kindComponentRoutes(KindBookRepository kindBookRepository) {
        return route()
                .GET("/api/kindBooks", accept(APPLICATION_JSON),
                        new KindHandler(kindBookRepository) :: list
                ).build();
    }

    private static class KindHandler {

        private final KindBookRepository repository;

        KindHandler(KindBookRepository repository) {
            this.repository = repository;
        }

        Mono<ServerResponse> list(ServerRequest request) {

            return ok().contentType(APPLICATION_JSON).body(repository.findAll(), KindBook.class);
        }
    }

}
