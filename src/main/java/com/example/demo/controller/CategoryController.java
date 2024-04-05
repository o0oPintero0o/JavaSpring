package com.example.demo.controller;

import com.example.demo.domain.Category;
import com.example.demo.dto.CategoryDto;
import com.example.demo.service.CategoryService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Category create(@RequestBody CategoryDto categoryDto) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDto, category);
        return categoryService.create(category);
    }
    @GetMapping()
    public ResponseEntity<?> getCategories(){
        return  new ResponseEntity<>(categoryService.findAll(),HttpStatus.OK);
    }
    @GetMapping("/page")
    public ResponseEntity<?> getCategories(
            @PageableDefault(size = 5, sort = "name", direction = Sort.Direction.ASC)
                    Pageable pageable){

        return  new ResponseEntity<>(categoryService.findAll((org.springframework.data.domain.Pageable) pageable),HttpStatus.OK);
    }
    @GetMapping("/{id}/get")
    public ResponseEntity<?> getCategories(@PathVariable("id") Long id){
        return new ResponseEntity<>( categoryService.findById(id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteById(id);
        return  new ResponseEntity<>("Category with Id+"+ id + "was deleted", HttpStatus.OK);
    }
}