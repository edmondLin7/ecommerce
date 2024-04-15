package com.thevarungupta.ecommerce.rest.api.service;

import com.thevarungupta.ecommerce.rest.api.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    Category getCategoryById(Long catId);

    Category createCategory(Category newCategory);

    Category updateCategory(Long catId, Category updateCategory);

    void deleteCategory(Long catId);
}
