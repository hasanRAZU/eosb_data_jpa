package com.sdlc.pro.eosb_data_jpa;

import com.sdlc.pro.eosb_data_jpa.entity.Course;
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
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;


// @SpringBootApplication
public class EosbDataJpaApplication {
    static final EntityManagerFactory entityMangerFactory = Persistence.createEntityManagerFactory("postgres-unit");
    static final EntityManager entityManager = entityMangerFactory.createEntityManager();

    private static Course course1, course2, course3, course4;
    private static Student student1, student2, student3, student4;
    private static List<Course> courseList1, courseList2, courseList3, courseList4;


    public static void main(String[] args) {
        //SpringApplication.run(EosbDataJpaApplication.class, args);

        createSomeCourses();
        createListonCourses();
        createSomeStudents();

        transactional(entityManager -> {


            persistSomeCourses(course1, course2, course3, course4);
            persistSomeStudents(student1, student2, student3, student4);




























        /*
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
            }*/


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


    private static void createSomeCourses() {
        course1 = new Course("Java");
        course2 = new Course("Algorithm");
        course3 = new Course("AI");
        course4 = new Course("Machine Learning");
    }

    private static void createSomeStudents() {
        student1 = new Student("Md", 3.1f, courseList1);
        student2 = new Student("Monjurul", 3.2f, courseList2);
        student3 = new Student("Hasan", 3.3f, courseList3);
        student4 = new Student("Razu", 3.4f, courseList4);
    }

    private static void createListonCourses() {
        courseList1 = new ArrayList<>();
        courseList1.add(course1);
        courseList1.add(course2);
        courseList1.add(course3);

        courseList2 = new ArrayList<>();
        courseList2.add(course2);
        courseList2.add(course3);
        courseList2.add(course4);

        courseList3 = new ArrayList<>();
        courseList3.add(course1);
        courseList3.add(course3);
        courseList3.add(course4);

        courseList4 = new ArrayList<>();
        courseList4.add(course1);
        courseList4.add(course2);
        courseList4.add(course4);
    }


    private static void persistSomeStudents(Student... students) {
        for (Student student : students) {
            entityManager.persist(student);
        }
    }

    private static void persistSomeCourses(Course... courses) {
        for (Course course : courses) {
            entityManager.persist(course);
        }
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