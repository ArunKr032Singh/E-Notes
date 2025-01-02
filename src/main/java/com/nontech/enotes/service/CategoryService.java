package com.nontech.enotes.service;

import java.util.List;

import com.nontech.enotes.entity.Category;

public interface CategoryService {
	
	public boolean saveCategory(Category category);

	public List<Category> getAllCategory();
}
