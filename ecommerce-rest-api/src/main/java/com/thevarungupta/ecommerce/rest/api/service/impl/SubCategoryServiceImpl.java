package com.thevarungupta.ecommerce.rest.api.service.impl;

import com.thevarungupta.ecommerce.rest.api.entity.Category;
import com.thevarungupta.ecommerce.rest.api.entity.SubCategory;
import com.thevarungupta.ecommerce.rest.api.repository.CategoryRepository;
import com.thevarungupta.ecommerce.rest.api.repository.SubCategoryRepository;
import com.thevarungupta.ecommerce.rest.api.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Override
    public List<SubCategory> getSubCategoriesByCatId(Long catId) {
        Category category = categoryRepository.findById(catId).orElse(null);
        if (category != null) {
            return subCategoryRepository.findByCategory(category);
        } else {
            return null; // Or you can throw an exception or return an empty list based on your requirements
        }
    }

    @Override
    public SubCategory getSubCategoryById(Long catId, Long subId) {
        Category category = categoryRepository.findById(catId).orElse(null);
        if (category != null) {
            return subCategoryRepository.findByIdAndCategory(subId, category);
        } else {
            return null;
        }
    }


    @Override
    public SubCategory createSubCategory(Long catId, SubCategory subCategory) {
        Category category = categoryRepository.findById(catId).orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));

        // Associate the sub-category with the category
        subCategory.setCategory(category);

        // Save the new sub-category
        return subCategoryRepository.save(subCategory);
    }

    @Override
    public SubCategory updateSubCategory(Long subId, SubCategory updateSubCategory) {
        // Check if the sub-category exists
        SubCategory existingSubCategory = subCategoryRepository.findById(subId)
                .orElseThrow(() -> new IllegalArgumentException("Sub-category not found"));

        // Update the existing sub-category attributes
        existingSubCategory.setSubCategoryName(updateSubCategory.getSubCategoryName());
        existingSubCategory.setSubCategoryDescription(updateSubCategory.getSubCategoryDescription());
        existingSubCategory.setSubCategoryImage(updateSubCategory.getSubCategoryImage());
        existingSubCategory.setPosition(updateSubCategory.getPosition());
        existingSubCategory.setStatus(updateSubCategory.isStatus());

        // Save the updated sub-category
        return subCategoryRepository.save(existingSubCategory);
    }

    @Override
    public void deleteSubCategory(Long subCatId) {
        // Check if the sub-category exists
        SubCategory subCategory = subCategoryRepository.findById(subCatId)
                .orElseThrow(() -> new IllegalArgumentException("Sub-category not found"));

        // Delete the sub-category
        subCategoryRepository.delete(subCategory);
    }
}
