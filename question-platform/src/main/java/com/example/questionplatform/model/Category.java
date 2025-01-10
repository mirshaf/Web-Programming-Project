package com.example.questionplatform.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
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
}
