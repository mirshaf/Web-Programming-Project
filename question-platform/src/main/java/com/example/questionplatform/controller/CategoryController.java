package com.example.questionplatform.controller;

import com.example.questionplatform.model.Category;
import com.example.questionplatform.model.Database;
import com.example.questionplatform.model.User;
import com.example.questionplatform.request.CategoryReq;
import com.example.questionplatform.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    Database database;

    @PostMapping()
    public Response addCategory(@RequestHeader("Authorization") String authHeader, @RequestBody CategoryReq categoryReq) {
        User user = database.getUser(authHeader);
        if (user == null)
            return new ErrorRes("Unauthenticated");

        return new AddCategoryRes("Category created successfully",
                new CategoryDTO(
                        database.addCategory(
                                categoryReq.getName(), categoryReq.getDescription(), categoryReq.getCreated_by()
                        )));
    }

    @DeleteMapping("/{id}")
    public Response deleteCategory(@RequestHeader("Authorization") String authHeader,
                            @PathVariable Integer id) {
        User user = database.getUser(authHeader);
        if (user == null)
            return new ErrorRes("Unauthenticated");

        Boolean result = database.deleteCategory(id);
        if (result == null) {
            return new ErrorRes("Category not found.");
        }
        else if (result) {
            return new MessageRes("Category deleted successfully");
        }
        else
            return new ErrorRes("Category cannot be deleted as it has associated questions");
    }

    @PutMapping("/{id}")
    public Response editCategory(@RequestHeader("Authorization") String authHeader,
                                 @PathVariable Integer id,
                                 @RequestBody CategoryReq categoryReq) {
        User user = database.getUser(authHeader);
        if (user == null)
            return new ErrorRes("Unauthenticated");

        Category category = database.editCategory(
                id, categoryReq.getName(), categoryReq.getDescription(), categoryReq.getCreated_by()
        );
        if (category == null) {
            return new ErrorRes("Category not found.");
        }
        return new AddCategoryRes("Category updated successfully",
                new CategoryDTO(category));
    }

    @GetMapping("/{id}")
    public Response editCategory(@RequestHeader("Authorization") String authHeader,
                                 @PathVariable Integer id) {
        User user = database.getUser(authHeader);
        if (user == null)
            return new ErrorRes("Unauthenticated");

        Category category = database.getCategory(id);
        if (category == null) {
            return new ErrorRes("Category not found.");
        }
        return new CategoryDTO(category);
    }

    @GetMapping("/my")
    public Response getMyCategories(@RequestHeader("Authorization") String authHeader) {
        User user = database.getUser(authHeader);
        if (user == null)
            return new ErrorRes("Unauthenticated");

        return new CategoriesRes(database.getCategoriesByUser(user.getId()));
    }

    @GetMapping()
    public Response getAllCategories(@RequestHeader("Authorization") String authHeader) {
        User user = database.getUser(authHeader);
        if (user == null)
            return new ErrorRes("Unauthenticated");

        return new CategoriesRes(database.getAllCategories());
    }
}
