package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.dto.ImageResponse;
import com.workintech.ecommerce.entity.Image;
import com.workintech.ecommerce.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {
    private ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{productId}")
    public List<ImageResponse> findByProductId(@PathVariable Long productId){
        return imageService.findByProductId(productId);
    }

    @PostMapping("/{productId}")
    public ImageResponse save(@PathVariable Long productId, @RequestBody Image image){
        return imageService.save(image,productId);
    }

    @DeleteMapping("/{imageId}")
    public ImageResponse delete(@PathVariable Long imageId){
        return imageService.delete(imageId);
    }

    @PutMapping("/{imageId}")
    public ImageResponse update(@PathVariable Long imageId, @RequestBody Image image){
        return imageService.update(imageId,image);
    }
}
