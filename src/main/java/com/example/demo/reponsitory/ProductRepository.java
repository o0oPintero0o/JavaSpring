package com.example.demo.reponsitory;

import com.example.demo.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.lang.model.element.Name;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByPriceAndId(double price, Long id);
    List<Product> findByNameAndId(String name, Long id);
}
