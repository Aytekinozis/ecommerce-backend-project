package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.dto.ProductResponse;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
    this.productService = productService;
    }

    @PostMapping("/{categoryId}")
    public ProductResponse save(@PathVariable Long categoryId,
                                @RequestBody Product product){
        return productService.save(product, categoryId);
    }

    @GetMapping
    public List<ProductResponse> findAll(){
        return productService.findAll();
    }

    @GetMapping("/{productId}")
    public ProductResponse find(@PathVariable Long productId){
        return productService.find(productId);
    }

    @DeleteMapping("/{productId}")
    public ProductResponse delete(@PathVariable Long productId){
        return productService.delete(productId);
    }

    @PutMapping("/{productId}")
    public ProductResponse update(@PathVariable Long productId,
                                @RequestBody Product product){
        return productService.update(productId, product);
    }
}
