package com.example.questionplatform.dto.response;

import lombok.Data;

@Data
public class AddCategoryRes implements Response{
    private String message;
    private CategoryDTO category;

    public AddCategoryRes(String message, CategoryDTO category) {
        this.message = message;
        this.category = category;
    }
}
