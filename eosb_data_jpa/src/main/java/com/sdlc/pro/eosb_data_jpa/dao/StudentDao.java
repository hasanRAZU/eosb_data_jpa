package com.sdlc.pro.eosb_data_jpa.dao;

import com.sdlc.pro.eosb_data_jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends JpaRepository<Student, Integer> {

    public static void main(String[] args) {
        System.out.println("Student Dao Added");
    }
}
