package ru.otus.springintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.springintegration.gateway.SendServiceGateWay;

import java.io.File;

@SpringBootApplication
public class SpringIntegrationApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringIntegrationApplication.class, args);
        SendServiceGateWay service = ctx.getBean(SendServiceGateWay.class);
        service.process(new File("D:\\Workspace\\Sandbox\\OtusTeam\\homework\\springwork-15\\spring-integration\\src\\main\\resources\\in\\address.csv"));
    }

}
