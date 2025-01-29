package com.nontech.enotes.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.nontech.enotes.exception.ResourceNotFoundException;
import com.nontech.enotes.util.Validation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.nontech.enotes.dto.CategoryDto;
import com.nontech.enotes.entity.Category;
import com.nontech.enotes.repository.CategoryRepo;
import com.nontech.enotes.response.CategoryResponse;
import com.nontech.enotes.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Validation validation;

    @Override
    public boolean saveCategory(CategoryDto categoryDto) {
        validation.categoryValidation(categoryDto);
        Category category = modelMapper.map(categoryDto, Category.class);
        if (ObjectUtils.isEmpty(category.getId())) {
            category.setIsDeleted(false);
            category.setCreatedBy(1);
            category.setCreatedOn(new Date());
        } else {
            updateCategory(category);
        }

        Category savedCategory = categoryRepo.save(category);
        if (ObjectUtils.isEmpty(savedCategory)) {
            return false;
        }
        return true;
    }

    private void updateCategory(Category category) {

        Optional<Category> findById = categoryRepo.findById(category.getId());
        if (findById.isPresent()) {
            Category existingCategory = findById.get();
            category.setCreatedBy(existingCategory.getCreatedBy());
            category.setCreatedOn(existingCategory.getCreatedOn());
            category.setIsDeleted(existingCategory.getIsDeleted());
            category.setUpdatedBy(1);
            category.setUpdatedOn(new Date());
        }
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepo.findByIsDeletedFalse();
        List<CategoryDto> categoruDtoList = categories.stream().map(cat -> modelMapper.map(cat, CategoryDto.class)).toList();
        return categoruDtoList;
    }

    @Override
    public List<CategoryResponse> getActiveCategory() {
        List<Category> categories = categoryRepo.findByIsActiveTrueAndIsDeletedFalse();
        System.out.println(categories);
        List<CategoryResponse> categoryList = categories.stream().map(cat -> modelMapper.map(cat, CategoryResponse.class)).toList();
        return categoryList;
    }

    @Override
    public CategoryDto getCategyById(Integer id) throws Exception {
        Category category = categoryRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        if (!ObjectUtils.isEmpty(category)) {
            return modelMapper.map(category, CategoryDto.class);
        }
        return null;
    }

    @Override
    public boolean deleteCategyById(Integer id) {
        Optional<Category> categoryById = categoryRepo.findById(id);
        if (categoryById.isPresent()) {
            Category category = categoryById.get();
            category.setIsDeleted(true);
            categoryRepo.save(category);
            return true;
        }
        return false;
    }


}
