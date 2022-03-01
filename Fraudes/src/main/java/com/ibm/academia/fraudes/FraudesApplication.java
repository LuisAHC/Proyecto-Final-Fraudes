package com.ibm.academia.fraudes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FraudesApplication {

    public static void main(String[] args) {
        SpringApplication.run(FraudesApplication.class, args);
    }

}
