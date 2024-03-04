package com.workintech.ecommerce.entity;

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
@Table(schema = "ecommerce", name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "title")
    @Size(min = 2,max = 45,message = "Title should be between 2 and 45")
    private String title;

    @Column(name = "img")
    @NotNull(message = "img cannot be left empty")
    private String img;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "gender")
    private String gender;

    @JsonManagedReference
    @OneToMany(mappedBy = "category", cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Product> products;


    public void addProduct(Product product){
        if(products == null){
            products = new ArrayList<>();
        }
        products.add(product);
    }

    public void deleteProduct(Product product){
        products.remove(product);
    }
}
