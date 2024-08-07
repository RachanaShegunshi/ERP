package com.bvb.erp.service;

import java.util.List;

import com.bvb.erp.dto.ProductDto;
import com.bvb.erp.exception.ErpException;
import com.bvb.erp.model.Product;

public interface ProductService {

	List<ProductDto> getAllProduct() throws ErpException;

	 String addNewProduct(Product product) throws ErpException;

	String updateProduct(Integer productId, Product product) throws ErpException;

	String deleteProduct(Integer productId)  throws ErpException;
	
	

}
