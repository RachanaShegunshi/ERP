package com.bvb.erp.service;

import java.util.List;

import com.bvb.erp.dto.EmployeeDto;
import com.bvb.erp.exception.ErpException;
import com.bvb.erp.model.Employee;

public interface EmployeeService {

	List<EmployeeDto> getAllEmployee() throws ErpException;

	String addNewEmployee(Employee employee) throws ErpException;

	String updateEmployee(Integer id, Employee employee) throws ErpException;
	
	String deleteEmployee(Integer id) throws ErpException;
}
