package com.bvb.erp.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderDto {
	
	private Integer id;
	private LocalDateTime orderDate;
	private BigDecimal totalAmount;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	

}
