package com.dh.demo_data.seeder;

import com.dh.demo_data.entity.Product;
import com.dh.demo_data.repository.ProductRepositorySeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProductSeeder implements CommandLineRunner {

    @Autowired
    ProductRepositorySeeder productRepositorySeeder;

    @Override
    public void run(String... args) throws Exception {
        truncateProductData();
        loadProductSeeder();
    }

    private void truncateProductData() {
        productRepositorySeeder.deleteAll();
    }

    private void loadProductSeeder() {
        if (productRepositorySeeder.count() == 0) {
            // Product product1 = new Product();
            // product1.setProductName("Product A");
            // product1.setPrice(100);
            // product1.setCountry("USA");
            // product1.setQuantity(80);
            
            // Product product2 = new Product();
            // product2.setProductName("Product B");
            // product2.setPrice(200);
            // product2.setCountry("UK");
            // product2.setQuantity(150);
            
            Product product1 = new Product("Product A",100, "USA",80);
            Product product2 = new Product("Product B",200, "UK",150);
            
            productRepositorySeeder.save(product1);
            productRepositorySeeder.save(product2);
        }
        System.out.println(productRepositorySeeder.count());
    }
}
