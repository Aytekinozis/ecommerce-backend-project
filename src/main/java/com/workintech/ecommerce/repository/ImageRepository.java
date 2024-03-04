package com.workintech.ecommerce.repository;

import com.workintech.ecommerce.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Long> {

    @Query(value = "SELECT i.* FROM ecommerce.image AS i WHERE i.product_id = :productId", nativeQuery = true)
    List<Image> findByProductId(Long productId);
}
