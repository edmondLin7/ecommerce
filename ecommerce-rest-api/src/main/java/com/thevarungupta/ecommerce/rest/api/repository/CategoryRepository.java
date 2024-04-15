package com.thevarungupta.ecommerce.rest.api.repository;

import com.thevarungupta.ecommerce.rest.api.entity.Category;
import com.thevarungupta.ecommerce.rest.api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
