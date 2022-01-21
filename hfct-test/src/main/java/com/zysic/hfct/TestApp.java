package com.zysic.hfct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(scanBasePackages = {"com.zysic"})
public class TestApp {
    public static void main(String[] args) {
        SpringApplication.run(TestApp.class);
    }
}
