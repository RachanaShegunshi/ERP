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

import com.bvb.erp.dto.EmployeeDto;
import com.bvb.erp.exception.ErpException;
import com.bvb.erp.model.Employee;
import com.bvb.erp.rest.ErpResponse;
import com.bvb.erp.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping
	public ErpResponse getAllEmployee() {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
           List<EmployeeDto> listOfEmployees = employeeService.getAllEmployee();
           status = HttpStatus.OK;
           return new ErpResponse(listOfEmployees, status);
		} catch (ErpException exception) {
			message = exception.getMessage();
		}
		return new ErpResponse(message, status);
	}

	@PostMapping()
	public ErpResponse addNewEmployee(@RequestBody Employee employee) {
		HttpStatus status = HttpStatus.BAD_GATEWAY;
		String message = "";
		try {
			message = employeeService.addNewEmployee(employee);
			status = HttpStatus.OK;
		} catch (Exception exception) {
			message = exception.getMessage();
		}
		return new ErpResponse(message, status);
	}

	@PutMapping(path = "/{employeeId}")
	public ErpResponse updateEmployee(@PathVariable("employeeId") Integer employeeId,
			@RequestBody Employee employee) {

		HttpStatus status = HttpStatus.BAD_GATEWAY;
		String message = "";
		try {
			message = employeeService.updateEmployee(employeeId, employee);
			status = HttpStatus.OK;

		} catch (Exception exception) {
			message = exception.getMessage();
		}
		return new ErpResponse(message, status);
	}
	
	@DeleteMapping("/{EmployeeId}")
	public ErpResponse deleteEmployee(@PathVariable("employeeId") Integer employeeId,
			@RequestBody Employee employee) {
		
		HttpStatus status = HttpStatus.BAD_GATEWAY;
		String message = "";
		try {
			message = employeeService.deleteEmployee(employeeId);
			status = HttpStatus.OK;
		}catch(Exception exception) {
			message = exception.getMessage();
		}
		return new ErpResponse(message, status);
		
	}

}
