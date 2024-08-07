package com.bvb.erp.ServiceImplementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bvb.erp.dao.CategoryRepository;
import com.bvb.erp.dto.CategoryDto;
import com.bvb.erp.exception.ErpException;
import com.bvb.erp.model.Category;
import com.bvb.erp.service.CategoryService;
import com.bvb.erp.util.CategoryUtil;

@Service
public class CategoryServiceImplementation implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<CategoryDto> getAllCategory() throws ErpException {
		try {
			List<CategoryDto> listOfDtos = categoryRepository
					                       .findAll() 
					                       .stream()
					                       .map(CategoryUtil::convertCategoryEntityToDto)
					                       .collect(Collectors.toList());
			
			if(listOfDtos == null) {
				throw new ErpException("Empty list");
			}
			return listOfDtos;
		} catch (ErpException exception) {
			throw new ErpException("Category doesnt exist");

		}
		
	}

	@Override
	public String addNewCategory(Category category) throws ErpException {
	
		try {
				Optional<Category> existingCategory = categoryRepository.findByCategoryName(category.getCategoryName());
				if (existingCategory.isPresent()) {
					throw new ErpException("product already exists");
				}
				categoryRepository.save(category);
			} catch (Exception exception) {
				throw new ErpException("Internal server error" + exception.getMessage());
			}
			return "Category Created Sucessfully";

		
	}

	@Override
	public String deleteCategory(Integer categoryId) throws ErpException {
		
		try {
			Category existingCategory = categoryRepository.findById(categoryId).get();
			if (existingCategory == null) {
				throw new ErpException("No data found");
			}
			categoryRepository.deleteById(categoryId);

		} catch (Exception exception) {
			throw new ErpException( "Internal server error" + exception.getMessage());
		}
		return "Category Deleted Successfully";

	}

	public String updateCategory(Integer categoryId, CategoryDto dto) throws ErpException {
		// TODO Auto-generated method stub
		try {
			Category existingCategory = categoryRepository.findById(categoryId).get();
			Category category = CategoryUtil.convertCategoryDtoToEntity(dto);
			
			if (category == null || existingCategory == null) {
				throw new ErpException("No data found");
			}

			if (dto.getCategoryName() != null && dto.getCategoryName() != existingCategory.getCategoryName()) {
				existingCategory.setCategoryName(dto.getCategoryName());
			}
			
			return "Category '" + existingCategory.getCategoryName() + "' has been successfully updated";
		} catch (Exception exception) {
			throw new ErpException( "Internal server error " + exception.getMessage());
		}
		
	}

	

	
	
	

}
