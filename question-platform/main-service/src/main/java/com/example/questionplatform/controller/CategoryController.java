package com.example.questionplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.questionplatform.dto.request.CategoryReq;
import com.example.questionplatform.dto.response.AddCategoryRes;
import com.example.questionplatform.dto.response.CategoriesRes;
import com.example.questionplatform.dto.response.CategoryDTO;
import com.example.questionplatform.dto.response.ErrorRes;
import com.example.questionplatform.dto.response.MessageRes;
import com.example.questionplatform.dto.response.Response;
import com.example.questionplatform.model.Category;
import com.example.questionplatform.model.Database;
import com.example.questionplatform.model.User;
import com.example.questionplatform.service.AuthorizationService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    Database database;

    @Autowired
    AuthorizationService authorizationService;

    @PostMapping()
    public Response addCategory(@RequestHeader("Authorization") String authHeader, @RequestBody CategoryReq categoryReq) {
        User user = database.getUser(authHeader);
        if (user == null)
            return new ErrorRes("Unauthenticated");

        if (!authorizationService.canCreateCategory(user)) {
            return new ErrorRes("Only designers can create categories");
        }

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

        if (!authorizationService.isDesigner(user)) {
            return new ErrorRes("Only designers can delete categories");
        }

        Category category = database.getCategory(id);
        if (category == null) {
            return new ErrorRes("Category not found");
        }

        if (!user.getId().equals(category.getCreated_by())) {
            return new ErrorRes("You can only delete your own categories");
        }

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

        if (!authorizationService.isDesigner(user)) {
            return new ErrorRes("Only designers can edit categories");
        }

        Category category = database.getCategory(id);
        if (category == null) {
            return new ErrorRes("Category not found");
        }

        if (!user.getId().equals(category.getCreated_by())) {
            return new ErrorRes("You can only edit your own categories");
        }

        category = database.editCategory(
                id, categoryReq.getName(), categoryReq.getDescription(), categoryReq.getCreated_by()
        );
        if (category == null) {
            return new ErrorRes("Category not found.");
        }
        return new AddCategoryRes("Category updated successfully",
                new CategoryDTO(category));
    }

    @GetMapping("/{id}")
    public Response getCategory(@RequestHeader("Authorization") String authHeader,
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

        if (!authorizationService.isDesigner(user)) {
            return new ErrorRes("Only designers can view their categories");
        }

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
