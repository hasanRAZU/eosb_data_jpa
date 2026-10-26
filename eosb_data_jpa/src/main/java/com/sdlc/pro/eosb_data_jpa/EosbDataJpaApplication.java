package com.sdlc.pro.eosb_data_jpa;

import com.sdlc.pro.eosb_data_jpa.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


// @SpringBootApplication
public class EosbDataJpaApplication {


    public static void main(String[] args) {
        //SpringApplication.run(EosbDataJpaApplication.class, args);

        Student student1 = new Student(null, "Razu", 3.6f);

        System.out.println(student1);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("postgres-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(student1);
            entityManager.getTransaction().commit();

        } catch (Exception ignored) {
        }
        entityManagerFactory.close();

    }
}