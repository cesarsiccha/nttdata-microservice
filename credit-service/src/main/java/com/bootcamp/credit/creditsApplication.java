package com.bootcamp.credit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class creditsApplication {

	public static void main(String[] args) {
		SpringApplication.run(creditsApplication.class, args);
	}

}
