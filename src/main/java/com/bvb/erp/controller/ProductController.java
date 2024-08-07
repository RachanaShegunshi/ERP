package com.bvb.erp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bvb.erp.dto.ProductDto;
import com.bvb.erp.exception.ErpException;
import com.bvb.erp.model.Product;
import com.bvb.erp.rest.ErpResponse;
import com.bvb.erp.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ErpResponse getAllProduct() {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
           List<ProductDto> listOfProducts = productService.getAllProduct();
           status = HttpStatus.OK;
           return new ErpResponse(listOfProducts, status);
		} catch (ErpException exception) {
			message = exception.getMessage();
		}
		return new ErpResponse(message, status);
	}

	@PostMapping
	public ErpResponse addNewProduct(@RequestBody Product product) {
		HttpStatus status = HttpStatus.BAD_GATEWAY;
		String message = "";
		try {
			message = productService.addNewProduct(product);
			status = HttpStatus.OK;
		} catch (Exception exception) {
			message = exception.getMessage();
		}
		return new ErpResponse(message, status);
	}

	@PutMapping(path = "/{productId}")
	public ErpResponse updateProduct(@PathVariable("productId") Integer productId,
			@RequestBody Product product) {

		HttpStatus status = HttpStatus.BAD_GATEWAY;
		String message = "";
		try {
			message = productService.updateProduct(productId, product);
			status = HttpStatus.OK;

		} catch (Exception exception) {
			message = exception.getMessage();
		}
		return new ErpResponse(message, status);
	}
	
	@DeleteMapping("/{ProductId}")
	public ErpResponse deleteProduct(@PathVariable("productId") Integer productId,
			@RequestBody Product product) {
		
		HttpStatus status = HttpStatus.BAD_GATEWAY;
		String message = "";
		try {
			message = productService.deleteProduct(productId);
			status = HttpStatus.OK;
		}catch(Exception exception) {
			message = exception.getMessage();
		}
		return new ErpResponse(message, status);
		
	}

	
	

}
