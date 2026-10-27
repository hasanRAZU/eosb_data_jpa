package com.sdlc.pro.eosb_data_jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private float cgpa;

    public Student(String name, float cgpa){
        this.name = name;
        this.cgpa = cgpa;
    }
}
