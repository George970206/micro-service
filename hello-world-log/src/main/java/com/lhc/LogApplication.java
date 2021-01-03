package com.lhc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lhc
 */
@SpringBootApplication
@RestController
public class LogApplication {

    private static final Logger log = LoggerFactory.getLogger(LogApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LogApplication.class, args);
    }

    @GetMapping("/hi")
    public String hi(){
        log.info("hello world!");
        return "Hi, George! Good morning!";
    }
}
