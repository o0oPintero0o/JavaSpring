package com.example.demo.service;

import com.example.demo.domain.Product;
import com.example.demo.reponsitory.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ProductService(ProductRepository repository){
        this.productRepository =repository;}
    @Transactional
    public Product create(Product product){

        return productRepository.save(product);
    }
    public Product findById(Long id){
        return productRepository.findById(id).orElse(new Product());
    }
    public List<Product> findByPrice(Double price, Long id ){
        return productRepository.findByPriceAndId(price, id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
    @org.springframework.transaction.annotation.Transactional
    public Product update(Product product, Long id) {
        var productPersisted = findById(id);
        if (!Objects.equals(productPersisted.getId(), id)) {
            return productPersisted;
        }
        BeanUtils.copyProperties(product, productPersisted, "id");

        return productRepository.save(productPersisted);
    }

    @org.springframework.transaction.annotation.Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
