package com.thevarungupta.ecommerce.rest.api.controller;

import com.thevarungupta.ecommerce.rest.api.entity.Category;
import com.thevarungupta.ecommerce.rest.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/api/v1/category")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        var data = categoryService.getAllCategories();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/{catId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("catId") Long catId){
        var data = categoryService.getCategoryById(catId);
        if(data == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        var data = categoryService.createCategory(category);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }
}
