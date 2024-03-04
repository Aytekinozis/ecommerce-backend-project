package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.ProductResponse;
import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductResponse> findAll();

    ProductResponse find(Long id);

    Product findByIdOriginal(Long id);

    ProductResponse save(Product product,Long categoryId);

    ProductResponse delete(Long id);

    ProductResponse update(Long id, Product product);

}
