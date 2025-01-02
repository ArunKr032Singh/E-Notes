package com.nontech.enotes.service;

import java.util.List;

import com.nontech.enotes.CategoryResponse;
import com.nontech.enotes.dto.CategoryDto;
import com.nontech.enotes.entity.Category;

public interface CategoryService {
	
	public boolean saveCategory(CategoryDto categoryDto);

	public List<CategoryDto> getAllCategory();

	public List<CategoryResponse> getActiveCategory();

	public CategoryDto getCategyById(Integer id);

	public boolean deleteCategyById(Integer id);
}
