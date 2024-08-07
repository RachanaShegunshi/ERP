package com.bvb.erp.service;

import java.util.List;

import com.bvb.erp.dto.CategoryDto;
import com.bvb.erp.exception.ErpException;
import com.bvb.erp.model.Category;

public interface CategoryService {

	List<CategoryDto> getAllCategory() throws ErpException;

	String addNewCategory(Category category) throws ErpException;

	String deleteCategory(Integer categoryId) throws ErpException;
	
	String updateCategory(Integer categoryId, CategoryDto dto) throws ErpException ;

}
