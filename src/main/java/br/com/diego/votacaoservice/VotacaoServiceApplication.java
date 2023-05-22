package br.com.diego.votacaoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;

@SpringBootApplication(exclude = LiquibaseAutoConfiguration.class)
public class VotacaoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotacaoServiceApplication.class, args);
	}

}
