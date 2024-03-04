package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.ProductResponse;
import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.exceptions.CategoryException;
import com.workintech.ecommerce.exceptions.ProductException;
import com.workintech.ecommerce.repository.ProductRepository;
import com.workintech.ecommerce.util.ProductConverter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;
    private CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }




    @Override
    public List<ProductResponse> findAll() {
        List<Product> products = productRepository.findAll();

        return ProductConverter.productListConvert(products);
    }

    @Override
    public ProductResponse find(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent()){

            Product product = productOptional.get();
            return ProductConverter.convertProduct(product);
        }

        throw new ProductException("Cannot find product with given id" + id, HttpStatus.NOT_FOUND);
    }


    @Override
    public Product findByIdOriginal(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            return productOptional.get();
        }
        throw new CategoryException("Product with given id is not exist: " + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public ProductResponse save(Product product,Long categoryId) {
        Category category = categoryService.findByIdOriginal(categoryId);
        category.addProduct(product);
        product.setCategory(category);
        productRepository.save(product);
        return ProductConverter.convertProduct(product);
    }

    @Override
    public ProductResponse delete(Long id) {
        ProductResponse productResponse = find(id);
        Product product = findByIdOriginal(id);
        Long catId = productResponse.categoryId();
        Category category = categoryService.findByIdOriginal(catId);
        category.deleteProduct(product);

        productRepository.deleteById(id);
        return productResponse;
    }

    @Override
    public ProductResponse update(Long id, Product product) {
        ProductResponse productResponse = find(id);
        product.setId(id);
        product.setCategory(categoryService.findByIdOriginal(productResponse.categoryId()));
        productRepository.save(product);
        return ProductConverter.convertProduct(product);
    }
}
