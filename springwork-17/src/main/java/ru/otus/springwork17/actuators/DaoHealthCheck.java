package ru.otus.springwork17.actuators;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.otus.springwork17.repository.BookDao;

import java.sql.SQLException;

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
