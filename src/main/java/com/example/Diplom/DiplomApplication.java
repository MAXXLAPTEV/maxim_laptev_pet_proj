package com.example.Diplom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jta.atomikos.AtomikosDependsOnBeanFactoryPostProcessor;

@SpringBootApplication
public class DiplomApplication {

	// TODO: 17.03.2022 fix all sonar issues

	public static void main(String[] args) {
		SpringApplication.run(DiplomApplication.class, args);
	}

}
