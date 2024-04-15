package com.thevarungupta.ecommerce.rest.api.repository;

import com.thevarungupta.ecommerce.rest.api.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import com.thevarungupta.ecommerce.rest.api.entity.Category;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    List<SubCategory> findByCategory(Category category);

    SubCategory findByIdAndCategory(Long subId, Category category);
}
