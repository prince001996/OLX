package com.olx.categories.Controller;


import com.olx.categories.DTO.CategoryDTO;
import com.olx.categories.Entity.Category;
import com.olx.categories.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/categories")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category/")
    public ResponseEntity<String> addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @GetMapping("/categories")
    public ResponseEntity<List> getCategories(){
        return categoryService.getAllCategory();
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable("categoryId") Long categoryId){
        return categoryService.getCategory(categoryId);
    }

    @PutMapping("/category/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable("categoryId") Long categoryId,  @RequestBody CategoryDTO categoryDTO){
        return categoryService.updateCategory(categoryId, categoryDTO);
    }

}
