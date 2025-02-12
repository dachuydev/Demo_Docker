package com.dh.demo_data.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String productName;

    @NonNull
    private Integer price;
    private String country;
    private int price_import;
    private int quantity;

    public Product(String productName, Integer price, String country, int price_import) {
        this.productName = productName;
        this.price = price;
        this.country = country;
        this.price_import = price_import;
    }

    public Product(String productName, int price, String country, int quantity) {
        this.productName = productName;
        this.price = price;
        this.country = country;
        this.quantity = quantity;
    }
}