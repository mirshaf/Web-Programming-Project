package com.example.questionplatform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Integer created_by; // User Id
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

    public Category() {
        // Default constructor for JPA
    }

    public Category(String name, String description, Integer created_by, List<Question> questions) {
        this.name = name;
        this.description = description;
        this.created_by = created_by;
        this.questions = questions;
    }
}
