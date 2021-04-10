package com.olx.categories.Service;

import com.olx.categories.Entity.Category;
import com.olx.categories.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public String addCategory(Category category) {
        categoryRepository.save(category);
        return "category added";
    }

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).get();
    }


}
