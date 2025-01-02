package com.nontech.enotes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nontech.enotes.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

	List<Category> findByIsActiveTrueAndIsDeletedFalse();

	Optional<Category> findByIdAndIsDeletedFalse(Integer id);

	List<Category> findByIsDeletedFalse();

}
