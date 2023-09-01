package com.example.mieszkanfinder;

import com.example.mieszkanfinder.sources.OLXSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MieszkanfinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MieszkanfinderApplication.class, args);
	}

}
