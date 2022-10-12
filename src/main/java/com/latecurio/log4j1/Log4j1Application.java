package com.latecurio.log4j1;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Log4j1Application {

	public static void main(String[] args) {
		BasicConfigurator.configure();
		SpringApplication.run(Log4j1Application.class, args);
	}

}
