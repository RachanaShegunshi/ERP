package com.bvb.erp.util;

import org.springframework.beans.BeanUtils;

import com.bvb.erp.dto.OrderDto;
import com.bvb.erp.model.Order;

public class OrderUtil {
	
	public static OrderDto convertOrderEntityToDto(Order order) {
		OrderDto dto = new OrderDto();
		BeanUtils.copyProperties(order, dto);
		return dto;
	}
	
	public static Order convertOrderDtoToEntity(OrderDto dto) {
		Order order = new Order();
		BeanUtils.copyProperties(dto, order);
		return order;
	}

}
