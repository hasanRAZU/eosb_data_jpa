package com.sdlc.pro.eosb_data_jpa;

import com.sdlc.pro.eosb_data_jpa.dao.StudentDao;
import com.sdlc.pro.eosb_data_jpa.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EosbDataJpaApplication implements CommandLineRunner
{

	@Autowired
	StudentDao studentDao;

	public static void main(String[] args) {
		SpringApplication.run(EosbDataJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		studentDao.save(new Student(null, "Monjurul", 3.6f));
		studentDao.save(new Student(null, "Hasan", 3.7f));
		studentDao.save(new Student(null, "Razu", 3.8f));
	}
}
