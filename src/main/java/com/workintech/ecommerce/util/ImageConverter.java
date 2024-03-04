package com.workintech.ecommerce.util;

import com.workintech.ecommerce.dto.ImageResponse;
import com.workintech.ecommerce.dto.ProductResponse;
import com.workintech.ecommerce.entity.Image;
import com.workintech.ecommerce.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ImageConverter {

    public static List<ImageResponse> imageListConvert(List<Image> images) {
        List<ImageResponse> imageResponses = new ArrayList<>();
        images.stream().forEach(image ->
                imageResponses.add(new ImageResponse(image.getUrl())));
        return imageResponses;
    }

    public static ImageResponse convertImage(Image image) {
        return new ImageResponse(image.getUrl());
    }
}
