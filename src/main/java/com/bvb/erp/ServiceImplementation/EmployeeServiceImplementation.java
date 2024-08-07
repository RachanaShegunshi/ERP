package com.bvb.erp.ServiceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bvb.erp.dao.EmployeeRepository;
import com.bvb.erp.dao.UserRepository;
import com.bvb.erp.dto.EmployeeDto;
import com.bvb.erp.dto.UserDto;
import com.bvb.erp.exception.ErpException;
import com.bvb.erp.model.Employee;
import com.bvb.erp.model.User;
import com.bvb.erp.service.EmployeeService;
import com.bvb.erp.util.EmployeeUtil;
import com.bvb.erp.util.UserUtil;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserServiceImplementation userServiceImplementation;

	@Override
	public List<EmployeeDto> getAllEmployee() throws ErpException {
		try {
			List<EmployeeDto> listOfDtos = employeeRepository.findAll().stream()
					.map(EmployeeUtil::convertEmployeeEntityToDto).collect(Collectors.toList());

			if (listOfDtos == null) {
				throw new ErpException("No Employees Found");
			}
			return listOfDtos;

		} catch (Exception exception) {
			throw new ErpException(exception.getMessage());

		}
	}

	@Override
	public String addNewEmployee(Employee employee) throws ErpException {

		try {
			User user = employee.getUser();
			if (user != null) {
				user = userRepository.saveAndFlush(user);
				employee.setUser(user);

				employeeRepository.save(employee);
				return "Employee" + employee.getUser().getFullName() + "has been created successfully";
			}
			return "Enter valid Data";

		} catch (Exception exception) {
			throw new ErpException("Employee already exists.");

		}

	}

	@Transactional
	public String updateEmployee(Integer id, Employee employee) throws ErpException {

		try {
			Employee existingEmployee = employeeRepository.findById(id).get();

			if (existingEmployee == null) {
				throw new ErpException("Employee Not Found");
			}

			Integer userId = existingEmployee.getUser().getId();
			UserDto dto = UserUtil.convertUserEntityToDto(existingEmployee.getUser());

			userServiceImplementation.updateUser(userId, dto);

			if (employee.getSalary() != null && employee.getSalary() != existingEmployee.getSalary()) {
				existingEmployee.setSalary(employee.getSalary());
			}

			return "Employee" + employee.getUser().getFullName() + "has been updated successfully";
		} catch (Exception exception) {
			throw new ErpException("Internal Server Error" + exception.getMessage());
		}
	}

	public String deleteEmployee(Integer id) throws ErpException {

		try {
			Employee existingEmployee = employeeRepository.findById(id).get();

			if (existingEmployee == null) {
				throw new ErpException("Employee Not Found");
			}
			Integer userId = existingEmployee.getUser().getId();
			
			employeeRepository.deleteById(id);
			
			userRepository.deleteById(userId);
			
			return "Employee" + existingEmployee.getUser().getFullName() + "has been deleted successfully";
			
		} catch (Exception exception) {
			throw new ErpException("Internal Server Error" + exception.getMessage());
		}
	}

}
