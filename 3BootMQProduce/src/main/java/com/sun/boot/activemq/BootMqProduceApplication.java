package com.sun.boot.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // 开启scheduled 注解
public class BootMqProduceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootMqProduceApplication.class, args);
    }

}
