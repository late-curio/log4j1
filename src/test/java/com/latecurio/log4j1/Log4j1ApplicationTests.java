package com.latecurio.log4j1;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Log4j1ApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HttpClient httpClient;

//	TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	void contextLoads() throws InterruptedException, IOException {
//		for (int i = 0; i < 5; i++) {
//			ResponseEntity<String> response =
//					restTemplate.getForEntity("http://localhost:" + port + "/info?message=Hi-" + System.currentTimeMillis(),
//							String.class);
//			System.out.println(response.getBody());
//			Thread.sleep(1000);
//		}


		final String uri = "https://localhost:10101";
		final HttpGet httpGet = new HttpGet(uri);
		httpGet.setProtocolVersion(HttpVersion.HTTP_1_0);
		final HttpParams params = new BasicHttpParams();
		params.setParameter("method", "test_data_get_scoped_metrics");
		params.setParameter("protocol_version", 17);
		httpGet.setParams(params);
		HttpResponse response = httpClient.execute(httpGet);

		Assertions.assertEquals(response.getStatusLine().getStatusCode(), 200);

//		Map<String, String> uriParam = new HashMap<>();
//		uriParam.put("account", "my_account");
//
//		UriComponents builder = UriComponentsBuilder.fromHttpUrl(uri)
//				.queryParam("method","get_metrics")
//				.queryParam("protocol_version","17").build();
//
//		HttpEntity<String> requestEntity = new HttpEntity<>(null);
//
//		ResponseEntity<String> validateResponse = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity,
//				String.class, uriParam);
////		ResponseEntity<String> validateResponse =
////				restTemplate.getForEntity("https://localhost:10101", String.class);
//		Assertions.assertEquals(200, validateResponse.getStatusCodeValue());
	}

}
