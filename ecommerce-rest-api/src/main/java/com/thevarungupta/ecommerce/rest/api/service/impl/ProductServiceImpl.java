package com.thevarungupta.ecommerce.rest.api.service.impl;

import com.thevarungupta.ecommerce.rest.api.entity.Category;
import com.thevarungupta.ecommerce.rest.api.entity.Product;
import com.thevarungupta.ecommerce.rest.api.entity.SubCategory;
import com.thevarungupta.ecommerce.rest.api.repository.CategoryRepository;
import com.thevarungupta.ecommerce.rest.api.repository.ProductRepository;
import com.thevarungupta.ecommerce.rest.api.repository.SubCategoryRepository;
import com.thevarungupta.ecommerce.rest.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCatId(Long catId) {
        Category category = categoryRepository.findById(catId).orElse(null);
        if (category != null) {
            return productRepository.findByCategory(category);
        } else {
            return null;
        }
    }

    @Override
    public List<Product> getProductsBySubId(Long subId) {
        SubCategory subCategory = subCategoryRepository.findById(subId).orElse(null);
        if (subCategory != null) {
            return productRepository.findBySubCategory(subCategory);
        } else {
            return null;
        }
    }

    @Override
    public Product createProduct(Long catId, Long subId, Product newProduct) {
        Category category = categoryRepository.findById(catId).orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
        SubCategory subCategory = subCategoryRepository.findById(subId).orElseThrow(() -> new IllegalArgumentException("Invalid sub-category ID"));

        newProduct.setCategory(category);
        newProduct.setSubCategory(subCategory);

        return productRepository.save(newProduct);
    }

    @Override
    public Product updateProduct(Product updateProduct) {
        // Check if the product exists
        Product existingProduct = productRepository.findById(updateProduct.getId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        // Update the existing product attributes
        existingProduct.setProductName(updateProduct.getProductName());
        existingProduct.setProductDescription(updateProduct.getProductDescription());
        existingProduct.setProductImage(updateProduct.getProductImage());
        existingProduct.setPrice(updateProduct.getPrice());
        existingProduct.setPosition(updateProduct.getPosition());
        existingProduct.setStatus(updateProduct.isStatus());

        // Save the updated product
        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        // Check if the product exists
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        // Delete the product
        productRepository.delete(product);
    }
}
