package ru.otus.springwork08;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongock
@SpringBootApplication
@EnableMongoRepositories
public class Springwork08Application {

    public static void main(String[] args) {
        SpringApplication.run(Springwork08Application.class, args);
    }

}
