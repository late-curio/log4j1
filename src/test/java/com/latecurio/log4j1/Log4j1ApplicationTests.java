package com.latecurio.log4j1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Log4j1ApplicationTests {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	void contextLoads() throws InterruptedException {
		for (int i = 0; i < 100; i++) {
			ResponseEntity<String> response =
					restTemplate.getForEntity("http://localhost:" + port + "/info?message=Hi-" + System.currentTimeMillis(),
							String.class);
			System.out.println(response.getBody());
			Thread.sleep(1000);
		}
	}

}
