package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.CategoryResponse;
import com.workintech.ecommerce.entity.Category;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> findAll();
    CategoryResponse findById(Long id);
    Category findByIdOriginal(Long id);

    CategoryResponse save(Category category);
    CategoryResponse delete(Long id);
}
