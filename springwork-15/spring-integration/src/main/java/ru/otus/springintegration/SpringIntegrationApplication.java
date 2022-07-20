package ru.otus.springintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.springintegration.gateway.SendServiceGateWay;
import ru.otus.springintegration.service.ControlService;

import java.io.File;

@SpringBootApplication
public class SpringIntegrationApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringIntegrationApplication.class, args);
        ControlService service = ctx.getBean(ControlService.class);
        service.run();
    }

}
