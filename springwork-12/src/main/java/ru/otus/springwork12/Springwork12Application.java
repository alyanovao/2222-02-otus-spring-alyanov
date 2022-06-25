package ru.otus.springwork12;

import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Springwork12Application {

    public static void main(String[] args) {
        String password = "password";
        String salt = "security";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        val hash = encoder.encode(password + salt);
        System.out.printf(hash);
        SpringApplication.run(Springwork12Application.class, args);
    }

}
