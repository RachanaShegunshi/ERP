package com.bvb.erp.service;

import java.util.List;

import com.bvb.erp.dto.UserDto;
import com.bvb.erp.exception.ErpException;
import com.bvb.erp.model.User;


public interface UserService {
	public abstract List<UserDto> getUsers() throws ErpException;
	
	 String addNewUser(User user) throws ErpException;
	 
	 String updateUser(Integer userId, UserDto dto) throws ErpException;
	 
	 String deleteUser(Integer userId) throws ErpException;
	
}
