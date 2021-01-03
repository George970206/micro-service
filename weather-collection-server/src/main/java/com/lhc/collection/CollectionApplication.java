package com.lhc.collection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author lhc
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class CollectionApplication {
    public static void main(String[] args) {
        SpringApplication.run(CollectionApplication.class, args);
    }
}
