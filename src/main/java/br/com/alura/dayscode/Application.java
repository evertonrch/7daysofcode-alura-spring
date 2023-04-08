package br.com.alura.dayscode;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

@SpringBootApplication
public class Application  {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	@Bean
	public PrintWriter printWriter() {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(file());
		}catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}
		return writer;
	}

	@Bean
	public File file() {
		File file = new File("/home/everton/content.html");
		return file;
	}

}
