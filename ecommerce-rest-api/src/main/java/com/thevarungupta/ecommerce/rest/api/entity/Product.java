package com.thevarungupta.ecommerce.rest.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "products"
)
public class Product {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(name = "product_name", nullable = false, unique = true)
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_image")
    private String productImage;

    private double price;

    private int position;

    private boolean status;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "category_id",
            nullable = false
    )
    private Category category;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "sub_category_id",
            nullable = false
    )
    private SubCategory subCategory;
}
