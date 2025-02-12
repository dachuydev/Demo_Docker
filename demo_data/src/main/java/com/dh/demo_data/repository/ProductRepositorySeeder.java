package com.dh.demo_data.repository;

import org.springframework.data.repository.CrudRepository;
import com.dh.demo_data.entity.Product;
 

public interface ProductRepositorySeeder extends CrudRepository<Product, Long> {
    
}
