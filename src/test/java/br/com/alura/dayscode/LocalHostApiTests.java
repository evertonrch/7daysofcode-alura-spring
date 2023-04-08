package br.com.alura.dayscode;

import br.com.alura.dayscode.model.ImdbMovie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LocalHostApiTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private ResponseEntity<ImdbMovie[]> response;

	@BeforeEach
	void ini() {
		this.response = this.restTemplate.getForEntity(URI.
				create("http://localhost:" + port + "/api/top250Movies"), ImdbMovie[].class);
	}

	@Test
	void shouldReturnOkAndBody() {
		assertEquals(HttpStatus.OK.value(), this.response.getStatusCodeValue());
		assertNotNull(this.response.getBody());
	}

	@Test
	void shouldReturn250Movies() {
		assertEquals(250, this.response.getBody().length);
	}
}
