package com.lhc.city;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author lhc
 */
@SpringBootApplication
@EnableEurekaClient
public class CityApplication {
    public static void main(String[] args) {
        SpringApplication.run(CityApplication.class, args);
    }
}
