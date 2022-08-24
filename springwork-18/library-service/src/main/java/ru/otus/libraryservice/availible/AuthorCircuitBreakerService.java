package ru.otus.libraryservice.availible;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import ru.otus.libraryservice.model.Author;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorCircuitBreakerService {

    @Value("${external.service.url}")
    private String url;

    private final RestOperations rest = new RestTemplate();

    public List<Author> findAll() {

        log.info("Start circuitBreaker findAll");
        try {
            val responseEntity = rest.getForEntity(url + "/authors", Author[].class);
            val res = responseEntity.getBody();
            return Arrays.asList(res);
        }
        catch (Exception e) {
            log.error("Error execute external service: " + e);
            return null;
        }
    }
}
