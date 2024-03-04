package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.ImageResponse;
import com.workintech.ecommerce.dto.ProductResponse;
import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.entity.Image;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.exceptions.CategoryException;
import com.workintech.ecommerce.exceptions.ImageException;
import com.workintech.ecommerce.exceptions.ProductException;
import com.workintech.ecommerce.repository.ImageRepository;
import com.workintech.ecommerce.util.ImageConverter;
import com.workintech.ecommerce.util.ProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService{

    private ImageRepository imageRepository;

    private ProductService productService;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository, ProductService productService) {
        this.imageRepository = imageRepository;
        this.productService = productService;
    }


    @Override
    public ImageResponse find(Long id) {
        Optional<Image> imageOptional = imageRepository.findById(id);
        if(imageOptional.isPresent()){

            Image image = imageOptional.get();
            return ImageConverter.convertImage(image);
        }

        throw new ImageException("Cannot find image with given id" + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public List<ImageResponse> findByProductId(Long productId) {
        Product product = productService.findByIdOriginal(productId);

        List<ImageResponse> imageResponses = new ArrayList<>();
        product.getImages().stream()
                .forEach(image -> imageResponses.add(new ImageResponse(image.getUrl())));

        return imageResponses;
    }

    @Override
    public Image findByIdOriginal(Long id) {
        Optional<Image> imageOptional = imageRepository.findById(id);
        if (imageOptional.isPresent()) {
            return imageOptional.get();
        }
        throw new ImageException("Image with given id is not exist: " + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public ImageResponse save(Image image, Long productId) {

        Product product = productService.findByIdOriginal(productId);
        image.setProduct(product);
        product.addImages(image);

        imageRepository.save(image);
        return ImageConverter.convertImage(image);
    }

    @Override
    public ImageResponse delete(Long id) {
        Image image = findByIdOriginal(id);
        Long proId = image.getProduct().getId();
        Product product = productService.findByIdOriginal(proId);
        product.deleteImage(image);
        imageRepository.deleteById(id);
        return ImageConverter.convertImage(image);
    }

    @Override
    public ImageResponse update(Long id, Image image) {
        Image imageOrj = findByIdOriginal(id);
        imageOrj.setUrl(image.getUrl());
        imageRepository.save(imageOrj);
        return ImageConverter.convertImage(imageOrj);
    }
}
