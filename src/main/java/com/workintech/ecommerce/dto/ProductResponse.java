package com.workintech.ecommerce.dto;

import com.workintech.ecommerce.entity.Image;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public record ProductResponse(String name, String description, Double price, Integer stock, Long categoryId, Double rating,
                              Integer sellCount, List<Image> images) {
}
