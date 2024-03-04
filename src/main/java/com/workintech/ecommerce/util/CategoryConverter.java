package com.workintech.ecommerce.util;

import com.workintech.ecommerce.dto.CategoryResponse;
import com.workintech.ecommerce.dto.ProductResponse;
import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoryConverter {

    public static List<CategoryResponse> categoryListConvert(List<Category> categories) {
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        categories.stream().forEach(category ->
                categoryResponses.add(new CategoryResponse(category.getTitle(),category.getImg(),category.getGender())));
        return categoryResponses;
    }

    public static CategoryResponse convertCategory(Category category) {
        return new CategoryResponse(category.getTitle(),category.getImg(),category.getGender());
    }
}
