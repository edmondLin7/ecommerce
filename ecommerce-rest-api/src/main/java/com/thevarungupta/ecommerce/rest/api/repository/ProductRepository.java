package com.thevarungupta.ecommerce.rest.api.repository;

import com.thevarungupta.ecommerce.rest.api.entity.Category;
import com.thevarungupta.ecommerce.rest.api.entity.Product;
import com.thevarungupta.ecommerce.rest.api.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);

    List<Product> findBySubCategory(SubCategory subCategory);
}
