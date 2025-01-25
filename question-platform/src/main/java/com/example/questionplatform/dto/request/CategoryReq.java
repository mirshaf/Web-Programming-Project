package com.example.questionplatform.dto.request;

import lombok.Data;

@Data
public class CategoryReq {
    private String name;
    private String description;
    private Integer created_by;
}
