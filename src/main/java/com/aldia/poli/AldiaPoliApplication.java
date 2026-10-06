package com.aldia.poli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AldiaPoliApplication {
    public static void main(String[] args) {
        SpringApplication.run(AldiaPoliApplication.class, args);
    }
}
