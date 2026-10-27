package com.sdlc.pro.eosb_data_jpa;

import com.sdlc.pro.eosb_data_jpa.entity.Student;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Transaction;

import java.util.function.Consumer;


// @SpringBootApplication
public class EosbDataJpaApplication {
    static final EntityManagerFactory entityMangerFactory = Persistence.createEntityManagerFactory("postgres-unit");
    static final EntityManager entityManager = entityMangerFactory.createEntityManager();

    public static void main(String[] args) {
        //SpringApplication.run(EosbDataJpaApplication.class, args);

        transactional(entityManager ->
        {

            var student = entityManager.find(Student.class, 15);

            student.setName("New Name Given");

            entityManager.detach(student);

            student.setCgpa(3.0f);

            entityManager.merge(student);

        });
    }


    static void transactional(Consumer<EntityManager> entityManagerConsumer) {

        // before advice
        entityManager.getTransaction().begin();

        try {
            // business logic
            entityManagerConsumer.accept(entityManager);
            entityManager.getTransaction().commit();

        } catch (Exception ex) {  // after throwing advice
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }

        //after advice
        entityMangerFactory.close();


    }
}

    /*

    @Entity
    Persistence
        EntityManagerFactory
            EntityManager
            EntityTransaction
                Query, TypedQuery
                @CriteriaQuery

    */


// Transaction Open -> Not Commited ???
// Data is stored in 'Buffered Cache in Database Engine' , also called 'First level Cache'