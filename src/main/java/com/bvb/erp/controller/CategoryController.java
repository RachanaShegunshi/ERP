package com.bvb.erp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bvb.erp.dto.CategoryDto;
import com.bvb.erp.exception.ErpException;
import com.bvb.erp.model.Category;
import com.bvb.erp.rest.ErpResponse;
import com.bvb.erp.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ErpResponse getAllCategory() {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
           List<CategoryDto> listOfCategories = categoryService.getAllCategory();
           status = HttpStatus.OK;
           return new ErpResponse(listOfCategories, status);
		} catch (ErpException exception) {
			message = exception.getMessage();
		}
		return new ErpResponse(message, status);
	}

	@PostMapping
	public ErpResponse addNewCategory(@RequestBody Category category) {
		HttpStatus status = HttpStatus.BAD_GATEWAY;
		String message = "";
		try {
			message = categoryService.addNewCategory(category);
			status = HttpStatus.OK;
		} catch (Exception exception) {
			message = exception.getMessage();
		}
		return new ErpResponse(message, status);
	}
	
	@DeleteMapping("/{CategoryId}")
	public ErpResponse deleteCategory(@PathVariable("categoryId") Integer categoryId,
			@RequestBody Category category) {
		
		HttpStatus status = HttpStatus.BAD_GATEWAY;
		String message = "";
		try {
			message = categoryService.deleteCategory(categoryId);
			status = HttpStatus.OK;
		}catch(Exception exception) {
			message = exception.getMessage();
		}
		return new ErpResponse(message, status);
		
	}
}
