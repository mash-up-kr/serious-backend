package com.sheennae.serious;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com.sheennae.serious")
@ComponentScan
@EnableAutoConfiguration
@Configuration
@EntityScan(
		basePackageClasses = { Jsr310JpaConverters.class }, basePackages = {"com.sheennae.serious.model"})
public class SeriousBackendApplication extends ServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SeriousBackendApplication.class, args);
	}

}
