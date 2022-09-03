package com.example.JavaDevHomeWork8;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

@SpringBootApplication
@RequiredArgsConstructor
public class JavaDevHomeWork8Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(JavaDevHomeWork8Application.class, args);
	}

//	@Bean
//	@Lazy
//	public AWSCredentials amazonAWSCredentials (@Value("${aws.access.key}") String awsKey,
//												@Value("${aws.access.secret.key}") String awsSecretKey){
//		return new BasicAWSCredentials(awsKey, awsSecretKey);
//	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(JavaDevHomeWork8Application.class);
	}

}