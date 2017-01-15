package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages="com.example.model")
@EnableJpaRepositories(basePackages="com.example.repository")
public class MyOauthExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyOauthExampleApplication.class, args);
	}
}
