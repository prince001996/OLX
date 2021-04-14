package com.olx.categories.Service;

import com.olx.categories.DTO.CategoryDTO;
import com.olx.categories.Entity.Category;
import com.olx.categories.Repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ModelMapper modelMapper;

    public ResponseEntity<String> addCategory(Category category) {
        categoryRepository.save(category);
        ResponseEntity<String> responseEntity = new ResponseEntity<>("Category added successfully", HttpStatus.OK);
        return responseEntity;
    }

    public ResponseEntity<List> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        ResponseEntity<List> responseEntity = new ResponseEntity<>(categories, HttpStatus.OK);
        return responseEntity;
    }

    public ResponseEntity<CategoryDTO> getCategory(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        CategoryDTO categoryDTO = new CategoryDTO();
        modelMapper.map(category.get(), categoryDTO);
        ResponseEntity<CategoryDTO> responseEntity = new ResponseEntity<>(categoryDTO, HttpStatus.OK);
        return responseEntity;
    }


    public ResponseEntity<String> updateCategory(Long categoryId, CategoryDTO categoryDTO) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isPresent()){
//            category.setId(categoryId);
            modelMapper.map(categoryDTO, category.get());
            categoryRepository.save(category.get());
            ResponseEntity<String> responseEntity = new ResponseEntity<>("Category Updated", HttpStatus.OK);
            return responseEntity;
        }
        ResponseEntity<String> responseEntity2 = new ResponseEntity<>("Category not present. Please create it!", HttpStatus.OK);
        return responseEntity2;
    }
}
