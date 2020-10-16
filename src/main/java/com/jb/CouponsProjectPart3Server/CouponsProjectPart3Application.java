package com.jb.CouponsProjectPart3Server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableScheduling
public class CouponsProjectPart3Application {

	// http://localhost:8080/swagger-ui.html
	public static void main(String[] args) {
		SpringApplication.run(CouponsProjectPart3Application.class, args);
		System.out.println("IOC Container Was Loaded!");
		
		
	}

}
