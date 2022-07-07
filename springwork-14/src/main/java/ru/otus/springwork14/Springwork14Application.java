package ru.otus.springwork14;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.sql.SQLException;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "ru.otus.springwork14.repository.jpa")
@EnableMongoRepositories(basePackages = "ru.otus.springwork14.repository.mongo")
public class Springwork14Application {

public static void main(String[] args) {
        SpringApplication.run(Springwork14Application.class, args);
    }
}
