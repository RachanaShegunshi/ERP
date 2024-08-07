package com.bvb.erp.ServiceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bvb.erp.dao.CategoryRepository;
import com.bvb.erp.dao.ProductRepository;
import com.bvb.erp.dto.CategoryDto;
import com.bvb.erp.dto.ProductDto;
import com.bvb.erp.exception.ErpException;
import com.bvb.erp.model.Category;
import com.bvb.erp.model.Product;
import com.bvb.erp.service.ProductService;
import com.bvb.erp.util.CategoryUtil;
import com.bvb.erp.util.ProductUtil;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImplementation implements ProductService{
	

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryServiceImplementation categoryServiceImplementation;

	@Override
	public List<ProductDto> getAllProduct() throws ErpException {
		try {
			List<ProductDto> listOfDtos = productRepository.findAll().stream()
					.map(ProductUtil::convertProductEntityToDto).collect(Collectors.toList());

			if (listOfDtos == null) {
				throw new ErpException("No Products Found");
			}
			return listOfDtos;

		} catch (Exception exception) {
			throw new ErpException(exception.getMessage());

		}
	}

	@Override
	public String addNewProduct(Product product) throws ErpException {

		try {
			Category category = product.getCategory();
			if (category != null) {
				category = categoryRepository.saveAndFlush(category);
				product.setCategory(category);

				productRepository.save(product);
				return "Product" + product.getCategory().getCategoryName() + "has been created successfully";
			}
			return "Enter valid Data";

		} catch (Exception exception) {
			throw new ErpException("Product already exists.");

		}

	}

	@Transactional
	public String updateProduct(Integer id, Product product) throws ErpException {

		try {
			Product existingProduct = productRepository.findById(id).get();

			if (existingProduct == null) {
				throw new ErpException("Product Not Found");
			}

			Integer categoryId = existingProduct.getCategory().getId();
			CategoryDto dto = CategoryUtil.convertCategoryEntityToDto(existingProduct.getCategory());

			categoryServiceImplementation.updateCategory(categoryId, dto);

			if (product.getProductName() != null && product.getProductName() != existingProduct.getProductName()) {
				existingProduct.setProductName(product.getProductName());
			}
            
			if (product.getPrice() != null && product.getPrice() != existingProduct.getPrice()) {
				existingProduct.setPrice(product.getPrice());
			}
			
			if (product.getQuantity() != null && product.getQuantity() != existingProduct.getQuantity()) {
				existingProduct.setQuantity(product.getQuantity());
			}
			return "Product" + product.getProductName() + "has been updated successfully";
		} catch (Exception exception) {
			throw new ErpException("Internal Server Error" + exception.getMessage());
		}
	}

	public String deleteProduct(Integer id) throws ErpException {

		try {
			Product existingProduct = productRepository.findById(id).get();

			if (existingProduct == null) {
				throw new ErpException("Employee Not Found");
			}
			Integer categoryId = existingProduct.getCategory().getId();
			
			productRepository.deleteById(id);
			
			categoryRepository.deleteById(categoryId);
			
			return "Product" + existingProduct.getProductName() + "has been deleted successfully";
			
		} catch (Exception exception) {
			throw new ErpException("Internal Server Error" + exception.getMessage());
		}
	}

	

	
}
