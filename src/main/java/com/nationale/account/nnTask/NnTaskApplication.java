package com.nationale.account.nnTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NnTaskApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(NnTaskApplication.class, args);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
