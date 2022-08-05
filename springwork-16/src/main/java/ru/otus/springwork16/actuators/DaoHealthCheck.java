package ru.otus.springwork16.actuators;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.otus.springwork16.repository.BookDao;

@RequiredArgsConstructor
@Component
public class DaoHealthCheck implements HealthIndicator {

    private final ApplicationContext context;

    @Override
    public Health health() {
        val dao = context.getBean("bookDao", BookDao.class);
        if (dao != null) {
            return Health.status(Status.UP).withDetail("message", "good").build();
        }
        else {
            return Health.status(Status.DOWN).withDetail("message", "bundle bookDao not valid").build();
        }
    }
}
