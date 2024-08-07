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

import com.bvb.erp.dto.UserDto;
import com.bvb.erp.exception.ErpException;
import com.bvb.erp.model.User;
import com.bvb.erp.rest.ErpResponse;
import com.bvb.erp.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ErpResponse getUsers(){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			List<UserDto> listOfUsers = userService.getUsers();
			status = HttpStatus.OK;
			return new ErpResponse(listOfUsers, status);
		} catch (ErpException exception) {
			message = exception.getMessage();
		}
		return new ErpResponse(message, status);
	}
    
	@PostMapping
	public ErpResponse registerNewUser(@RequestBody User user) {
		HttpStatus status = HttpStatus.BAD_GATEWAY;
		String message ="";
		try {
		message = userService.addNewUser(user);
		status = HttpStatus.OK;
		}catch(ErpException exception) {
			message = exception.getMessage();
		}
		return new ErpResponse(message, status);
		
	}
	
	@PutMapping(path = "/{userId}")
	public ErpResponse updateUser(@PathVariable("userId") Integer userId, @RequestBody UserDto userDto) {

		HttpStatus status = HttpStatus.BAD_GATEWAY;
		String message = "";
		try {
			message = userService.updateUser(userId, userDto);
			status = HttpStatus.OK;
		}catch(ErpException exception) {
			message = exception.getMessage();
		}

		return new ErpResponse(message, status);
	}
	
	@DeleteMapping("/{userId}")
	public ErpResponse deleteUser(@PathVariable("userId") Integer userId,@RequestBody UserDto userDto) {
		
		HttpStatus status = HttpStatus.BAD_GATEWAY;
		String message = "";
		try {
			message = userService.deleteUser(userId);
			status = HttpStatus.OK;
		}catch(ErpException exception) {
			message = exception.getMessage();
		}
		return new ErpResponse(message, status);
	}
}
