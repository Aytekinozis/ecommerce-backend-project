package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.CategoryResponse;
import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.exceptions.CategoryException;
import com.workintech.ecommerce.repository.CategoryRepository;

import com.workintech.ecommerce.util.CategoryConverter;
import com.workintech.ecommerce.util.ProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<CategoryResponse> findAll() {
        List<Category> categories = categoryRepository.findAll();

        return CategoryConverter.categoryListConvert(categories);
    }

    @Override
    public CategoryResponse findById(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            return CategoryConverter.convertCategory(category);
        }
        throw new CategoryException("Category with given id is not exist: " + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public Category findByIdOriginal(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            return categoryOptional.get();
        }
        throw new CategoryException("Category with given id is not exist: " + id, HttpStatus.NOT_FOUND);
    }



    @Override
    public CategoryResponse save(Category category) {
        categoryRepository.save(category);
        return CategoryConverter.convertCategory(category);
    }

    @Override
    public CategoryResponse delete(Long id) {
        CategoryResponse categoryResponse = findById(id);
        categoryRepository.deleteById(id);
        return categoryResponse;
    }
}
