package com.workintech.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "ecommerce", name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    @NotNull
    @Size(min = 3,message = "minimum 3 characters")
    private String name;
    @Column(name = "phone")
    @NotNull
    @Pattern(regexp = "^(\\+90|0)?\\s*(\\(\\d{3}\\)[\\s-]*\\d{3}[\\s-]*\\d{2}[\\s-]*\\d{2}|\\(\\d{3}\\)[\\s-]*\\d{3}[\\s-]*\\d{4}|\\(\\d{3}\\)[\\s-]*\\d{7}|\\d{3}[\\s-]*\\d{3}[\\s-]*\\d{4}|\\d{3}[\\s-]*\\d{3}[\\s-]*\\d{2}[\\s-]*\\d{2})$",message = "enter valid phone number")
    private String phone;
    @Column(name = "tax_no")
    @NotNull
    @Pattern(regexp = "[T]\\d{4}[V]\\d{6}$",message = "enter a valid tax no")
    private String tax_no;
    @Column(name = "bank_account")
    @NotNull
    private String bank_account;

    @OneToOne(mappedBy = "store", cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    private ApplicationUser applicationUser;
}
