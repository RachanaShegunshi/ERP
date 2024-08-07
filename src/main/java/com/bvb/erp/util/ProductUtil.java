package com.bvb.erp.util;

import org.springframework.beans.BeanUtils;

import com.bvb.erp.dto.ProductDto;
import com.bvb.erp.model.Product;


public class ProductUtil {
	
	public static ProductDto convertProductEntityToDto(Product product) {
		ProductDto dto = new ProductDto();
		BeanUtils.copyProperties(product, dto);
		return dto;
	}
	
	public static Product convertProductDtoToEntity(ProductDto dto) {
		Product product = new Product();
		BeanUtils.copyProperties(dto, product);
		return product;
	}

}
