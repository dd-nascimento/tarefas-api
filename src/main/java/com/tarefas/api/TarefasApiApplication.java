package com.tarefas.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class TarefasApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TarefasApiApplication.class, args);
	}

}
