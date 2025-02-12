package com.dh.demo_data.req;

import lombok.Data;

@Data
public class ProductUpdateReq {
    private int id;
    private String productName;
    private int price;
}
