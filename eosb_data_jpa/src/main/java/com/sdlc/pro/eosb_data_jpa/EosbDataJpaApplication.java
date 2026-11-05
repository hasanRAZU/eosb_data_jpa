package com.sdlc.pro.eosb_data_jpa;

import com.sdlc.pro.eosb_data_jpa.entity.Student;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Transaction;
import org.hibernate.engine.spi.PersistenceContext;
import org.hibernate.engine.spi.SessionImplementor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


// @SpringBootApplication
public class EosbDataJpaApplication {
    static final EntityManagerFactory entityMangerFactory = Persistence.createEntityManagerFactory("postgres-unit");
    static final EntityManager entityManager = entityMangerFactory.createEntityManager();

    public static void main(String[] args) {
        //SpringApplication.run(EosbDataJpaApplication.class, args);

        transactional(entityManager ->
        {


            entityManager.find(Student.class, 11);
            entityManager.find(Student.class, 13);
            entityManager.find(Student.class, 15);





            // Check all the Entity which are in 'Persistent' state
            SessionImplementor session =
                    entityManager.unwrap(SessionImplementor.class);

            PersistenceContext persistenceContext =
                    session.getPersistenceContext();

            for (Object entity : persistenceContext.getEntitiesByKey().values()) {
                System.out.println("Entity: " + entity);
            }
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