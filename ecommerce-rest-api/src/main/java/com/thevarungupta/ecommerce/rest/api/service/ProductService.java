package com.thevarungupta.ecommerce.rest.api.service;

import com.thevarungupta.ecommerce.rest.api.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    List<Product> getProductsByCatId(Long catId);

    List<Product> getProductsBySubId(Long subId);

    Product createProduct(Long catId, Long subId, Product newProduct);

    Product updateProduct(Product updateProduct);

    void deleteProduct(Long productId);
}
