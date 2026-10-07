package com.sdlc.pro.eosb_data_jpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EosbDataJpaApplication implements CommandLineRunner
{

	public static void main(String[] args) {
		SpringApplication.run(EosbDataJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Command Line Runner Added");
	}
}
