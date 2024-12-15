package com.example.questionplatform.model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    static int idCounter = 1;
    private Integer id;
    private String name;
    private String description;
    private Integer created_by; // User Id
    private List<Question> questions;

    public Category(String name, String description, Integer created_by, List<Question> questions) {
        this.id = idCounter++;
        this.name = name;
        this.description = description;
        this.created_by = created_by;
        this.questions = new ArrayList<>();
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCreated_by() {
        return created_by;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
