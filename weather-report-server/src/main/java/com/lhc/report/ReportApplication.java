package com.lhc.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 主应用程序.
 * 
 * @since 1.0.0 2017年9月27日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportApplication.class, args);
	}
}
