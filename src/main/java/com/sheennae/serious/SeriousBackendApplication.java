package com.sheennae.serious;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(
		basePackageClasses = { Jsr310JpaConverters.class }, basePackages = {"com.sheennae.serious.model"})
public class SeriousBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(SeriousBackendApplication.class, args);
	}
}
