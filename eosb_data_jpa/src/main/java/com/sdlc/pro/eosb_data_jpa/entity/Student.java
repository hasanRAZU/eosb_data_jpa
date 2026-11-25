package com.sdlc.pro.eosb_data_jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import jakarta.persistence.JoinColumn;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@DynamicUpdate
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private float cgpa;

    @OneToMany
    @JoinColumn(name = "courselist_id", referencedColumnName = "id")
    private List<Course> courseList;

    public Student(String name, float cgpa, List<Course> courses){
        this.name = name;
        this.cgpa = cgpa;
        this.courseList = courses;
    }

}
