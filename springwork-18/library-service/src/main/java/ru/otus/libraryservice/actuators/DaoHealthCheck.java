package ru.otus.libraryservice.actuators;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.otus.libraryservice.repository.BookDao;

@RequiredArgsConstructor
@Component
public class DaoHealthCheck implements HealthIndicator {

    private final BookDao dao;

    @Override
    public Health health() {
        try {
            val books = dao.findAll();
            return Health.status(Status.UP).withDetail("message", "good").build();
        } catch (Exception e) {
            return Health.status(Status.DOWN).withDetail("message", "bundle bookDao not valid").build();
        }
    }
}
