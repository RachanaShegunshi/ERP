package com.bvb.erp.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bvb.erp.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	@Query("select c from Category c where c.categoryName = ?1")
	Optional<Category> findByCategoryName(String categoryName);

}
