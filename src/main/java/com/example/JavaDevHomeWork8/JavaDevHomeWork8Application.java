package com.example.JavaDevHomeWork8;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
@RequiredArgsConstructor
public class JavaDevHomeWork8Application {

	private final DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(JavaDevHomeWork8Application.class, args);
	}

//	@Bean
//	public void flywayMigrate(){
//		System.err.println("MIGRATION!!!");
//		System.out.println("dataSource = " + dataSource.toString());
//		Flyway.configure().baselineOnMigrate(true).dataSource(dataSource).load().repair();
//	}
}