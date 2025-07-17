package com.graymatter.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.graymatter.demo"})
public class MasterProject {

	public static void main(String[] args) {
		SpringApplication.run(MasterProject.class, args);
	}

}
