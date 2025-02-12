package com.dh.demo_data.req;

import lombok.Data;

@Data
public class ProductCreateReq {
    private String productName;
    private int price;
}
