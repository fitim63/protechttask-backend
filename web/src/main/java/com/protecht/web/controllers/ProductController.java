package com.protecht.web.controllers;

import com.protecht.domain.Product;
import com.protecht.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/createProduct")
    public ResponseEntity<?> createApartment(@RequestBody Product product) {
        logger.info("Creating product: {}", product);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping(value = "/products")
    public ResponseEntity<?> viewAllProducts() {
        List<Product> products = productService.getAllProducts();

        if (products.isEmpty()) {
            logger.info("There are no products!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping(value = "/products/editProduct/{id}")
    public ResponseEntity<?> updateProductById(@PathVariable("id") int id, @RequestBody Product p) {
        Product product = productService.getById(id);
        if (product == null) {
            logger.info("Product to be edited doesnt exist!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        productService.editProductById(id, p);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping(value = "/products/deleteProduct/{id}")
    public ResponseEntity<?> deleteProductByID(@PathVariable("id") int id) {
        Product product = productService.getById(id);
        if (product == null) {
            logger.info("Product to be deleted doesnt exist!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        productService.deleteById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
