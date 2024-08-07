package com.bvb.erp.util;

import org.springframework.beans.BeanUtils;

import com.bvb.erp.dto.UserDto;
import com.bvb.erp.model.User;

public class UserUtil {
	
	public static UserDto convertUserEntityToDto(User user) {
		UserDto dto = new UserDto();
		BeanUtils.copyProperties(user, dto);
		return dto;
	}
	
	public static User convertUserDtoToEntity(UserDto dto) {
		User user = new User();
		BeanUtils.copyProperties(dto, user);
		return user;
	}
		
}


