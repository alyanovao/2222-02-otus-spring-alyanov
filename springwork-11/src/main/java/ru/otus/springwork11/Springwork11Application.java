package ru.otus.springwork11;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableMongock
@EnableReactiveMongoRepositories
@SpringBootApplication
public class Springwork11Application {

    public static void main(String[] args) {
        SpringApplication.run(Springwork11Application.class, args);
    }

}
