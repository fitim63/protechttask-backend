package com.protecht.service;

import com.protecht.domain.Product;
import com.protecht.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getById(int id) {
        return productRepository.findById(id);
    }

    public void deleteById(int id) {
        productRepository.deleteById(id);
    }

    public void save(Product p) {
        Product product = new Product();

        product.setName(p.getName());
        product.setPrice(p.getPrice());

        productRepository.save(p);
    }

    public void editProductById(int id, Product p){
        Product product = productRepository.findById(id);
        product.setName(p.getName());
        product.setPrice(p.getPrice());
        productRepository.save(product);
    }
}
