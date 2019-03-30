package com.altimetrik.transaction.config;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SawaggerConfig {

	public Docket apiDoc() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.altimetrik.transaction"))
				.paths(regex("/api.*"))
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"Transaction API Endpoints", 
				"", 
				"1.0", 
				"https://www.altimatrik.com/terms", 
				"Ashok Adhikari",
				"Altimatrik Open License", 
				"https://www.altimatrik.com/licenses");
	}
}
