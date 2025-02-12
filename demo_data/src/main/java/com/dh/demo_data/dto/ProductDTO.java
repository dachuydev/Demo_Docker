package com.dh.demo_data.dto;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NonNull;

@Data
public class ProductDTO {
    private Integer id;

    private String productName;

    private Integer price;
}
