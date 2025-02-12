package com.dh.demo_data.controller;

import com.dh.demo_data.dto.ProductDTO;
import com.dh.demo_data.entity.Product;
import com.dh.demo_data.repository.ProductRepository;
import com.dh.demo_data.req.ProductCreateReq;
import com.dh.demo_data.req.ProductUpdateReq;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("product/page")
    public ResponseEntity<?> paging(Pageable pageable) {
        System.out.println("pageable");
        System.out.println(pageable);
        Page<Product> products = productRepo.findAll(pageable);
        System.out.println(products);
        return ResponseEntity.ok(products);
    }

//    @GetMapping("product")
//    public ResponseEntity<?> getAll(@RequestParam String productName,
//                                    @RequestParam int price) {
//        System.out.println(productName);
//        System.out.println(price);
//        List<Product> products = productRepo.findAll();
//        System.out.println(products);
//
//        List<Product> productsVN = productRepo.findByCountry("VN");
//        System.out.println("productsVN");
//        System.out.println(productsVN);
//
//        List<Product> productsPrice = productRepo.findByPriceGreaterThan(200);
//        System.out.println("Price > 200");
//        System.out.println(productsPrice);
//
//        productRepo.updateData("Làm việc 2");
//
//
//        return new ResponseEntity<>(products, HttpStatus.OK);
//
//    }

    @GetMapping("product")
    public ResponseEntity<?> getAll() {
        List<Product> products = productRepo.findAll();

        List<ProductDTO> productDTOS = new ArrayList<>();

        // Cách thông thường
//        products.forEach(product -> {
//            ProductDTO productDTO = new ProductDTO();
//            productDTO.setId(product.getId());
//            productDTO.setProductName(product.getProductName());
//            productDTO.setPrice(product.getPrice());
//
//            productDTOS.add(productDTO);
//        });

        // dùng model mapper
        productDTOS = modelMapper.map(products, new TypeToken<List<ProductDTO>>() {}.getType());

        Product product = productRepo.findById(1).get();
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        System.out.println("productDTO");
        System.out.println(productDTO);
        return ResponseEntity.ok(productDTOS);
    }

    @GetMapping("product/{id}")
    public ResponseEntity<?> getDataById(@PathVariable int id) {
        System.out.println("id: " + id);

        Optional<Product> opProduct = productRepo.findById(id);
        if (opProduct.isPresent()) {
            Product product = opProduct.get();
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not found product id: " + id, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("product")
    public ResponseEntity<?> create(@RequestBody ProductCreateReq productCreateReq) {
        Optional<Product> opProduct = productRepo.getData(productCreateReq.getProductName());
        System.out.println(opProduct);

        if (opProduct.isEmpty()) {
            Product product = new Product();
            product.setProductName(productCreateReq.getProductName());
            product.setPrice(productCreateReq.getPrice());

            productRepo.save(product);
            return new ResponseEntity<>("Save product successfully: " + product.getProductName(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Product already exists: " + productCreateReq.getProductName(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("product")
    public ResponseEntity<?> update(@RequestBody ProductUpdateReq productUpdateReq) {
        Optional<Product> opProduct = productRepo.findById(productUpdateReq.getId());
        if (opProduct.isEmpty()) {
            return new ResponseEntity<>("Not found product with id: " + productUpdateReq.getId(), HttpStatus.BAD_REQUEST);
        }

        Product product = opProduct.get();
        product.setProductName(productUpdateReq.getProductName());
        product.setPrice(productUpdateReq.getPrice());

//        if (productUpdateReq.getProductName() != null){
//            product.setProductName(productUpdateReq.getProductName());
//        }
//        if (productUpdateReq.getPrice() != null){
//            product.setPrice(productUpdateReq.getPrice());
//        }
        productRepo.save(product);
        return new ResponseEntity<>("Update successfully: " + product.getProductName(), HttpStatus.OK);
    }

    @DeleteMapping("product/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Optional<Product> opProduct = productRepo.findById(id);

        if (opProduct.isEmpty()) {
            return new ResponseEntity<>("Not found product with id: " + id, HttpStatus.BAD_REQUEST);
        }
        productRepo.deleteById(id);
        return ResponseEntity.ok("Delete successfully: " + id);
    }
}
