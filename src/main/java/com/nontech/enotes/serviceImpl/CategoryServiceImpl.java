package com.nontech.enotes.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.nontech.enotes.CategoryResponse;
import com.nontech.enotes.dto.CategoryDto;
import com.nontech.enotes.entity.Category;
import com.nontech.enotes.repository.CategoryRepo;
import com.nontech.enotes.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public boolean saveCategory(CategoryDto categoryDto) {
		
//		Category category = new Category();
//		category.setName(categoryDto.getName());
//		category.setDescription(categoryDto.getDescription());
//		category.setActive(categoryDto.isActive());
		
		Category category = modelMapper.map(categoryDto, Category.class);
		
		category.setDeleted(false);
		category.setCreatedBy(1);
		category.setCreatedOn(new Date());
//		category.setActive(categoryDto.isActive());
		
//		System.out.println(category);
		
		Category savedCategory = categoryRepo.save(category);
//		System.out.println(savedCategory);
		if(ObjectUtils.isEmpty(savedCategory)) {
			return false;
		}
		return true;
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = categoryRepo.findByIsDeletedFalse();
		List<CategoryDto> categoruDtoList = categories.stream().map(cat->modelMapper.map(cat, CategoryDto.class)).toList();
		return categoruDtoList;
	}

	@Override
	public List<CategoryResponse> getActiveCategory() {
		List<Category> categories = categoryRepo.findByIsActiveTrueAndIsDeletedFalse();
		System.out.println(categories);
		List<CategoryResponse> categoryList = categories.stream().map(cat->modelMapper.map(cat, CategoryResponse.class)).toList();
		return categoryList;
	}

	@Override
	public CategoryDto getCategyById(Integer id) {
		Optional<Category> categoryById = categoryRepo.findByIdAndIsDeletedFalse(id);
		if(categoryById.isPresent()) {
			Category category = categoryById.get();
			return modelMapper.map(categoryById, CategoryDto.class);
		}
		return null;
	}

	@Override
	public boolean deleteCategyById(Integer id) {
		Optional<Category> categoryById = categoryRepo.findById(id);
		if(categoryById.isPresent()) {
			Category category = categoryById.get();
			category.setDeleted(true);
			categoryRepo.save(category);
			return true;
		}
		return false;
	}
	
	

}
