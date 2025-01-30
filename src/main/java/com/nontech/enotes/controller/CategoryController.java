package com.nontech.enotes.controller;

import com.nontech.enotes.dto.CategoryDto;
import com.nontech.enotes.exception.ResourceNotFoundException;
import com.nontech.enotes.response.CategoryResponse;
import com.nontech.enotes.service.CategoryService;
import com.nontech.enotes.util.CommonUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity<?> saveCategory(@RequestBody CategoryDto categoryDto) {

        Boolean savedCategory = categoryService.saveCategory(categoryDto);
        if (savedCategory) {
            return CommonUtil.createBuildResponseMessage("Saved success", HttpStatus.CREATED);
//            return new ResponseEntity<>("Saved success", HttpStatus.OK);
        } else {
            return CommonUtil.createErrorResponseMessage("Not saved", HttpStatus.INTERNAL_SERVER_ERROR);
//            return new ResponseEntity<>("Saved failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllCategory() {
        List<CategoryDto> categoryDtoList = categoryService.getAllCategory();
        if (CollectionUtils.isEmpty(categoryDtoList)) {
            return ResponseEntity.noContent().build();
        } else {
            return CommonUtil.createBuildResponse(categoryDtoList, HttpStatus.OK);
//            return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);
        }
    }

    @GetMapping("/active")
    public ResponseEntity<?> getActiveCategory() {
        List<CategoryResponse> categoryDtoList = categoryService.getActiveCategory();
        if (CollectionUtils.isEmpty(categoryDtoList)) {
            return ResponseEntity.noContent().build();
        } else {
//            return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);
            return CommonUtil.createBuildResponse(categoryDtoList, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryDetailsById(@PathVariable Integer id) throws Exception {
//        try {
//            CategoryDto categoryDto = categoryService.getCategyById(id);
//            if (ObjectUtils.isEmpty(categoryDto)) {
//                return new ResponseEntity<>("Category not found with id= " + id, HttpStatus.NOT_FOUND);
//            }
//            return new ResponseEntity<>(categoryDto, HttpStatus.OK);
//        } catch (ResourceNotFoundException ex) {
//            log.error("Controlller :: getCategoryDetailsById ::", ex.getMessage());
//            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//        } catch (Exception ex) {
//            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }

        CategoryDto categoryDto = categoryService.getCategyById(id);
        if (ObjectUtils.isEmpty(categoryDto)) {
//            return new ResponseEntity<>("Internal Server Error", HttpStatus.NOT_FOUND);
            return CommonUtil.createErrorResponseMessage("Internal Server Error", HttpStatus.NOT_FOUND);
        }
//        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
        return CommonUtil.createBuildResponse(categoryDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Integer id) {
        boolean deleted = categoryService.deleteCategyById(id);
        if (deleted) {
//            return new ResponseEntity<>("Category deleted", HttpStatus.OK);
            return CommonUtil.createBuildResponse("Category deleted success",HttpStatus.OK);
        }
//        return new ResponseEntity<>("Category not deleted ", HttpStatus.INTERNAL_SERVER_ERROR);
        return CommonUtil.createErrorResponseMessage("Category not deleted ", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
