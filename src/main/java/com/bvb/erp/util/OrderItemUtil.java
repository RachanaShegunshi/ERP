package com.bvb.erp.util;

import org.springframework.beans.BeanUtils;

import com.bvb.erp.dto.OrderItemDto;
import com.bvb.erp.model.OrderItem;

public class OrderItemUtil {
	
	public static OrderItemDto convertOrderItemEntityToDto(OrderItem orderItem) {
		OrderItemDto dto = new OrderItemDto();
		BeanUtils.copyProperties(orderItem, dto);
		return dto;
	}
	
	public static OrderItem convertOrderItemDtoToEntity(OrderItem dto) {
		OrderItem orderItem = new OrderItem();
		BeanUtils.copyProperties(dto, orderItem);
		return orderItem;
	}

}
