package com.thevarungupta.ecommerce.rest.api.service;

import com.thevarungupta.ecommerce.rest.api.entity.SubCategory;

import java.util.List;

public interface SubCategoryService {
    List<SubCategory> getSubCategoriesByCatId(Long catId);

    SubCategory getSubCategoryById(Long CatId, Long subId);

    SubCategory createSubCategory(Long catId, SubCategory subCategory);

    SubCategory updateSubCategory(Long subId, SubCategory updateSubCategory);

    void deleteSubCategory(Long subCatId);
}
