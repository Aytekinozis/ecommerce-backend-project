package com.workintech.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "ecommerce", name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @Size(min = 2,max = 45,message = "Product name should be between 2 and 45")
    private String name;

    @Column(name = "description")
    @NotNull(message = "description cannot be null")
    private String description;

    @Column(name = "price")
    @NotNull(message = "price cannot be null")
    private Double price;

    @Column(name = "stock")
    @NotNull(message = "stock cannot be null")
    private Integer stock;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "sell_count")
    private Integer sellCount;

    @JsonManagedReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

    public void addImages(Image image){
        if(images == null){
            images = new ArrayList<>();
        }
        images.add(image);
    }

    public void deleteImage(Image image){
        images.remove(image);
    }

    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "category_id")
    private Category category;


}
