package com.example.questionplatform.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CategoriesRes implements Response {
    List<CategoryDTO2> categories;

    public CategoriesRes(List<CategoryDTO2> categories) {
        this.categories = categories;
    }
}
