package com.example.compulsory11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/players")
public class Compulsory11Application {
	private static List<String> registeredPlayers = new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(Compulsory11Application.class, args);
		getRegisteredPlayers().add("Player 1");
		getRegisteredPlayers().add("TEoo");
		getRegisteredPlayers().add("TEoo22");
	}

	@GetMapping("/hello")
	public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
	@GetMapping
	public static List<String> getRegisteredPlayers() {
		return registeredPlayers;
	}

}