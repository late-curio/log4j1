package com.latecurio.log4j1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;


@SpringBootApplication
public class Log4j1Application {

	public static void main(String[] args) throws IOException {
		//BasicConfigurator.configure();
		ClassPathResource resource = new ClassPathResource("log4j.properties");
		//PropertyConfigurator.configure(resource.getInputStream());
		SpringApplication.run(Log4j1Application.class, args);
	}

}
