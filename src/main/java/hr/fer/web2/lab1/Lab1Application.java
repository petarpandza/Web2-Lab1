package hr.fer.web2.lab1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lab1Application {

	public static void main(String[] args) {
		Init.run();
		SpringApplication.run(Lab1Application.class, args);
	}

}
