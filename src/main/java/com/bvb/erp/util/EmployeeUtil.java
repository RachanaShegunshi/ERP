package com.bvb.erp.util;

import org.springframework.beans.BeanUtils;

import com.bvb.erp.dto.EmployeeDto;
import com.bvb.erp.model.Employee;

public class EmployeeUtil {
	
	public static EmployeeDto convertEmployeeEntityToDto(Employee employee) {
		EmployeeDto dto = new EmployeeDto();
		BeanUtils.copyProperties(employee, dto);
		return dto;
	}
	
	public static Employee convertEmployeeDtoToEntity(EmployeeDto dto) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(dto, employee);
		return employee;
	}

}
