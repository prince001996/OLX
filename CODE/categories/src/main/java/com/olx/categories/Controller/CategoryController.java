package com.olx.categories.Controller;


import com.olx.categories.Entity.Category;
import com.olx.categories.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category/")
    public String addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @GetMapping("/categories")
    public List<Category> getCategory(){
        return categoryService.getAllCategory();
    }

    @GetMapping("/category/{categoryId}")
    public Category getCategory(@PathVariable("categoryId") Long categoryId){
        return categoryService.getCategory(categoryId);
    }


}
