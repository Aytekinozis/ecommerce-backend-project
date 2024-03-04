package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.ImageResponse;
import com.workintech.ecommerce.dto.ProductResponse;
import com.workintech.ecommerce.entity.Image;
import com.workintech.ecommerce.entity.Product;

import java.util.List;

public interface ImageService {

    ImageResponse find(Long id);

    List<ImageResponse> findByProductId(Long id);

    Image findByIdOriginal(Long id);

    ImageResponse save(Image image,Long productId);

    ImageResponse delete(Long id);

    ImageResponse update(Long id, Image Image);
}
