package com.nontech.enotes.service;

import java.util.List;

import com.nontech.enotes.dto.CategoryDto;
import com.nontech.enotes.entity.Category;
import com.nontech.enotes.exception.ResourceNotFoundException;
import com.nontech.enotes.response.CategoryResponse;

public interface CategoryService {
	
	public boolean saveCategory(CategoryDto categoryDto);

	public List<CategoryDto> getAllCategory();

	public List<CategoryResponse> getActiveCategory();

	public CategoryDto getCategyById(Integer id) throws Exception;

	public boolean deleteCategyById(Integer id);
}
