package com.thevarungupta.ecommerce.rest.api.controller;

import com.thevarungupta.ecommerce.rest.api.entity.SubCategory;
import com.thevarungupta.ecommerce.rest.api.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subcategories")
public class SubCategoryController {
    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping("/{categoryId}")
    public ResponseEntity<List<SubCategory>> getSubCategoriesByCategoryId(@PathVariable Long categoryId) {
        List<SubCategory> subCategories = subCategoryService.getSubCategoriesByCatId(categoryId);
        return new ResponseEntity<>(subCategories, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}/{subCategoryId}")
    public ResponseEntity<SubCategory> getSubCategoryById(@PathVariable Long categoryId, @PathVariable Long subCategoryId) {
        SubCategory subCategory = subCategoryService.getSubCategoryById(categoryId, subCategoryId);
        if (subCategory != null) {
            return new ResponseEntity<>(subCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{categoryId}")
    public ResponseEntity<SubCategory> createSubCategory(@PathVariable Long categoryId, @RequestBody SubCategory subCategory) {
        SubCategory createdSubCategory = subCategoryService.createSubCategory(categoryId, subCategory);
        return new ResponseEntity<>(createdSubCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{subCategoryId}")
    public ResponseEntity<SubCategory> updateSubCategory(@PathVariable Long subCategoryId, @RequestBody SubCategory subCategory) {
        SubCategory updatedSubCategory = subCategoryService.updateSubCategory(subCategoryId, subCategory);
        return new ResponseEntity<>(updatedSubCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{subCategoryId}")
    public ResponseEntity<Void> deleteSubCategory(@PathVariable Long subCategoryId) {
        subCategoryService.deleteSubCategory(subCategoryId);
        return ResponseEntity.noContent().build();
    }
}
