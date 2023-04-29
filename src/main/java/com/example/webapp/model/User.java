package com.example.webapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Comparator;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "age")
    private int age;

    @Column(name = "name")
    private String name;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
