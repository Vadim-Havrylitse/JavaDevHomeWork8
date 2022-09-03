package com.example.JavaDevHomeWork8;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@RequiredArgsConstructor
public class JavaDevHomeWork8Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(JavaDevHomeWork8Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(JavaDevHomeWork8Application.class);
	}

}