package com.foods.ldbakes;

import com.foods.ldbakes.Model.User;
import com.foods.ldbakes.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class LdbakesApplication {
	public static void main(String[] args) {
		SpringApplication.run(LdbakesApplication.class, args);
	}
}
