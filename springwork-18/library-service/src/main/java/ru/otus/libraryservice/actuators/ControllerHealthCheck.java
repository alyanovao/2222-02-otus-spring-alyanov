package ru.otus.libraryservice.actuators;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.otus.libraryservice.controller.BookController;

@RequiredArgsConstructor
@Component
public class ControllerHealthCheck implements HealthIndicator {

    private final ApplicationContext context;

    @Override
    public Health health() {
        val book = context.getBean("bookController", BookController.class);
        if (book != null) {
            return Health.status(Status.UP).withDetail("message", "good").build();
        }
        else {
            return Health.status(Status.DOWN).withDetail("message", "bundle bookController not valid").build();
        }
    }
}
