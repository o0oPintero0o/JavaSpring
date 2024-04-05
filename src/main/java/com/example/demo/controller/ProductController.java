package com.example.demo.controller;

import com.example.demo.domain.Product;
import com.example.demo.dto.ProductCreateUpdate;
import com.example.demo.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {
    @Autowired
    private ProductService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Product create(@RequestBody ProductCreateUpdate productCreate) {
        Product product = new Product();
        BeanUtils.copyProperties(productCreate, product);
        return service.create(product);
    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> findById(@Param(value = "price") double price,
                                  @Param(value = "id") Long id){
        return service.findByPrice(price,id);
    }

    @GetMapping(path = "/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> findAll(){
        return service.findAll();
    }

    @PutMapping(path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Product update(@PathVariable(value = "id") Long id, @RequestBody ProductCreateUpdate productUpdate) {
        Product product = new Product();
        BeanUtils.copyProperties(productUpdate, product);
        return service.update(product, id);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(value = "id") Long id){
        service.delete(id);
    }

}