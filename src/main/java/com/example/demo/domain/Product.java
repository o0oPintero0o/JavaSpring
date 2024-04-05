package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
@Data
@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false, length = 255)
    private String name;

    @Column(length = 255, nullable = false)
    private Double price;

    @Column
    private String description;
    @Column
    private String detail;

    public Product(){

    }


}
