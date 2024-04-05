package com.example.demo.service;

import com.example.demo.Exception.CategoryException;
import com.example.demo.domain.Category;
import com.example.demo.reponsitory.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;


    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Category create(Category category) {
        return repository.save(category);
    }

    public Category update(Long id,Category category){
        Optional<Category> existed = repository.findById(id);
        if(existed.isEmpty()){
            throw new CategoryException("Category id" + id + "does not exist");

        }
        try{
            Category existedCategory = existed.get();
            existedCategory.setName(category.getName());
            existedCategory.setStatus(category.getStatus());

            return repository.save(existedCategory);
        }catch (Exception ex){
            throw new CategoryException("Category is update fail");

        }
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Page<Category> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Category findById(Long id) {
        Optional<Category> found = repository.findById(id);
        if(found.isEmpty()){
            throw new CategoryException("Category with id" + id + "does not exist");
        }
        return found.get();
    }
    public void deleteById(Long id){
        Category existed = findById(id);

        repository.delete(existed);
    }
}
