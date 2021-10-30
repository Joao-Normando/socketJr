package com.joao.normando.springSocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories

public class SpringSocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSocketApplication.class, args); }

}
