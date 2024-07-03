package edu2.innotech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = "edu2.innotech")
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Main.class, args);

        FileLoader fl = (FileLoader) ctx.getBean("fileLoader");
        fl.doFileLoadProcess();
    }
}