package com.bvb.erp.util;

import org.springframework.beans.BeanUtils;

import com.bvb.erp.dto.CategoryDto;
import com.bvb.erp.model.Category;

public class CategoryUtil {
	
	public static CategoryDto convertCategoryEntityToDto(Category category) {
		CategoryDto dto = new CategoryDto();
		BeanUtils.copyProperties(category, dto);
		return dto;
	}
	
	public static Category convertCategoryDtoToEntity(CategoryDto dto) {
		Category category = new Category();
		BeanUtils.copyProperties(dto, category);
		return category;
	}

}
