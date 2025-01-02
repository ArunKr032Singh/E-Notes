package com.nontech.enotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nontech.enotes.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
