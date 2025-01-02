package com.nontech.enotes.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.nontech.enotes.entity.Category;
import com.nontech.enotes.repository.CategoryRepo;
import com.nontech.enotes.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public boolean saveCategory(Category category) {
		System.out.println(category+" category");
		category.setDeleted(false);
		Category savedCategory = categoryRepo.save(category);
		if(ObjectUtils.isEmpty(savedCategory)) {
			return false;
		}
		return true;
	}

	@Override
	public List<Category> getAllCategory() {
		List<Category> categories = categoryRepo.findAll();
		return categories;
	}

}
