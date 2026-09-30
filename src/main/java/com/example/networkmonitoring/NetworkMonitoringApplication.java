package com.example.networkmonitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching 
@SpringBootApplication
public class NetworkMonitoringApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetworkMonitoringApplication.class, args);
    }
}
