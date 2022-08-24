package ru.otus.libraryservice.availible;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import ru.otus.libraryservice.model.Author;
import ru.otus.libraryservice.model.KindBook;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class KindBookCircuitBreakerService {

    @Value("${external.service.url}")
    private String url;

    private final RestOperations rest = new RestTemplate();

    public List<KindBook> findAll() {

        log.info("Start circuitBreaker findAll");
        try {
            val responseEntity = rest.getForEntity(url + "/kindBooks", KindBook[].class);
            val res = responseEntity.getBody();
            return Arrays.asList(res);
        }
        catch (Exception e) {
            log.error("Error execute external service: " + e);
            return null;
        }
    }
}
