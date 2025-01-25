package com.example.questionplatform.response;

import lombok.Data;

@Data
public class CategoryDTO2 {
    private Integer id;
    private String name;
    private String description;
    private int questionCount;

    public CategoryDTO2() {}

    public CategoryDTO2(Integer id, String name, String description, int questionCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.questionCount = questionCount;
    }
}
