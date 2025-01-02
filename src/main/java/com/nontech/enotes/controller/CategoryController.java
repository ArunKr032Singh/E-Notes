package com.nontech.enotes.controller;

import com.nontech.enotes.CategoryResponse;
import com.nontech.enotes.dto.CategoryDto;
import com.nontech.enotes.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save-category")
    public ResponseEntity<?> saveCategory(@RequestBody CategoryDto categoryDto) {
    	System.out.println("Hellloooooo");
    	System.out.println(categoryDto+" DTOOOOOOOOOOOOOOOOOOOO");
    	System.out.println("Hellloooooo");
        boolean savedCategory = categoryService.saveCategory(categoryDto);
        if (savedCategory) {
            return new ResponseEntity<>("Saved success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Saved failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllCategory(){
        List<CategoryDto> categoryDtoList = categoryService.getAllCategory();
        if(CollectionUtils.isEmpty(categoryDtoList)){
            return ResponseEntity.noContent().build();
        }else{
            return new ResponseEntity<>(categoryDtoList,HttpStatus.OK);
        }
    }
    
    @GetMapping("/active")
    public ResponseEntity<?> getActiveCategory(){
        List<CategoryResponse> categoryDtoList = categoryService.getActiveCategory();
        if(CollectionUtils.isEmpty(categoryDtoList)){
            return ResponseEntity.noContent().build();
        }else{
            return new ResponseEntity<>(categoryDtoList,HttpStatus.OK);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryDetailsById(@PathVariable Integer id){
    	CategoryDto categoryDto = categoryService.getCategyById(id);
    	if(ObjectUtils.isEmpty(categoryDto)) {
    		return new ResponseEntity<>("Category not found with id= "+id, HttpStatus.NOT_FOUND);
    	}
    	return new ResponseEntity<>(categoryDto, HttpStatus.NOT_FOUND);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryDetailsById(@PathVariable Integer id){
    	boolean deleted = categoryService.deleteCategyById(id);
    	if(deleted) {
    		return new ResponseEntity<>("Category deleted", HttpStatus.OK);
    	}
    	return new ResponseEntity<>("Category not deleted ", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
}
