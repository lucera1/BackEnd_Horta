package com.estagio.horta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "com.estagio")
@EntityScan(basePackages = {"com.estagio.domains","com.estagio.domains.enums"})
@EnableJpaRepositories(basePackages = "com.estagio.repositories")
@SpringBootApplication
public class HortaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HortaApplication.class, args);
	}

}
