package com.shinhansec.marketcapitalization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MarketCapitalizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketCapitalizationApplication.class, args);
	}
}
