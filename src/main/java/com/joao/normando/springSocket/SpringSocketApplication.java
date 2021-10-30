package com.joao.normando.springSocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableMongoRepositories

public class SpringSocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSocketApplication.class, args); }

}
