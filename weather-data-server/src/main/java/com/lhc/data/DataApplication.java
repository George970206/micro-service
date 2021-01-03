package com.lhc.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 天气数据查询服务只需要从Redis中获取数据
 * @author lhc
 */
@SpringBootApplication
@EnableEurekaClient
public class DataApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataApplication.class, args);
    }
}
