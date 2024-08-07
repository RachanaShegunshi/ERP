package com.bvb.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bvb.erp.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
