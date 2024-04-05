package com.example.demo.reponsitory;

import com.example.demo.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}
