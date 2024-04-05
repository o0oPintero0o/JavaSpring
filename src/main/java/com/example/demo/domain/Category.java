package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column( name = "name",nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", nullable = false)
    private CategoryStatus status;

}