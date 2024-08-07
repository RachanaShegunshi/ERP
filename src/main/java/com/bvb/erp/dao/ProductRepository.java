package com.bvb.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bvb.erp.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
