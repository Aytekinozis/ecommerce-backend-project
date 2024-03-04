package com.workintech.ecommerce.util;

import com.workintech.ecommerce.dto.ProductResponse;
import com.workintech.ecommerce.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductConverter {

    public static List<ProductResponse> productListConvert(List<Product> products) {
        List<ProductResponse> productResponses = new ArrayList<>();
        products.stream().forEach(product ->
                productResponses.add(new ProductResponse(product.getName(),product.getDescription(),product.getPrice(),
                        product.getStock(),product.getCategory().getId(),product.getRating(),product.getSellCount(),product.getImages())));
        return productResponses;
    }

    public static ProductResponse convertProduct(Product product) {
        return new ProductResponse(product.getName(),product.getDescription(),product.getPrice(),
                product.getStock(),product.getCategory().getId(),product.getRating(),product.getSellCount(),product.getImages());
    }
}
