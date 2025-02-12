package com.dh.demo_data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dh.demo_data.entity.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

    @Query(value = "from Product where productName = :productName")
    Optional<Product>getData(String productName);

    List<Product> findByCountry(String country);
    List<Product> findByPrice(Integer price);

    List<Product> findByPriceGreaterThan(Integer price);

    @Modifying
    @Query(value = "UPDATE Product SET price = 1111 WHERE productName = :productName")
    void updateData(String productName);



}

