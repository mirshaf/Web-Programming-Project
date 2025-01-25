package com.example.questionplatform.response;

import com.example.questionplatform.model.Category;
import lombok.Data;

@Data
public class CategoryDTO implements Response {
    private Integer id;
    private String name;
    private String description;

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
    }
}
