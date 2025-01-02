package com.nontech.enotes.controller;

import com.nontech.enotes.entity.Category;
import com.nontech.enotes.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save-category")
    public ResponseEntity<?> saveCategory(@RequestBody Category category) {
        boolean savedCategory = categoryService.saveCategory(category);
        if (savedCategory) {
            return new ResponseEntity<>("Saved success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Saved failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/category")
    public ResponseEntity<?> getAllCategory(){
        List<Category> categories = categoryService.getAllCategory();
        if(CollectionUtils.isEmpty(categories)){
            return ResponseEntity.noContent().build();
        }else{
            return new ResponseEntity<>(categories,HttpStatus.OK);
        }
    }
}
