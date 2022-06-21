package ru.otus.springwork11;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.otus.springwork11.model.Author;
import ru.otus.springwork11.repository.AuthorRepository;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyExtractors.toMono;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class AuthorConfig {

    @Bean
    public RouterFunction<ServerResponse> authorComponentRoutes(AuthorRepository repository) {
        return route()
                .GET("/api/authors", accept(APPLICATION_JSON),
                        new AuthorHandler(repository)::list
                )
                .GET("/api/author/{id}", accept(APPLICATION_JSON),
                        request -> repository.findById(request.pathVariable("id"))
                                .flatMap(author -> ok().contentType(APPLICATION_JSON).body(fromValue(author))
                                        .switchIfEmpty(notFound().build()))
                )
                .POST("/api/author",
                        req -> req.body(toMono(Author.class))
                        .doOnNext(repository::save)
                        .then(ok().contentType(APPLICATION_JSON).build())
                ).build();
    }

    private static class AuthorHandler {
        private final AuthorRepository repository;

        public AuthorHandler(AuthorRepository repository) {
            this.repository = repository;
        }

        Mono<ServerResponse> list(ServerRequest request) {
            return ok().contentType(APPLICATION_JSON).body(repository.findAll(), Author.class);
        }
    }
}

