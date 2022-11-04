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

import javax.net.SocketFactory;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

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
	public void testMockCollector() throws KeyManagementException, IOException, NoSuchAlgorithmException {
		SSLContext sslContext = SSLContext.getInstance("TLS");
		sslContext.init(null, new TrustManager[]{new UnsafeJavaTrustManager()}, null);
		SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

		HttpsURLConnection post = (HttpsURLConnection) new URL("https://localhost:10101?method=test_data_get_scoped_metrics&protocol_version=17").openConnection();
		post.setSSLSocketFactory(sslSocketFactory);
		post.connect();
		int responseCode = post.getResponseCode();

		//String body = post.getInputStream()

		Assertions.assertEquals(200, responseCode);

		post.disconnect();
	}


}
