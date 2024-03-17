package ru.inno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.inno.controller.ControllerAccount;
import ru.inno.repository.*;

import java.sql.*;

@SpringBootApplication(scanBasePackages = "ru.inno")
public class Main {
    public static void main(String[] args) throws SQLException {
        ApplicationContext ctx = SpringApplication.run(Main.class);
        //TppRefProductRegisterTypeRepo repo = ctx.getBean(TppRefProductRegisterTypeRepo.class);
        //repo.findAll().forEach(System.out::println);

    }
}