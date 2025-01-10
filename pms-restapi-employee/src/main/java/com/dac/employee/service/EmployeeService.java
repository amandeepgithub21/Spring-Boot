package com.dac.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dac.employee.model.Employee;
import com.dac.employee.repository.EmployeeRepository;


@Service
public class EmployeeService {




private final EmployeeRepository employeeRepository;

public EmployeeService(EmployeeRepository employeeRepository) {
	
	this.employeeRepository = employeeRepository;
} 


public Employee saveEmployee(Employee p) {
	return employeeRepository.save(p); 
}


public List<Employee> listAll(){
	return employeeRepository.findAll();
	}


   public Optional<Employee> getSingleEmployee(long pid) {
	   return employeeRepository.findById(pid);       
   }
   
   public void deleteEmployee(long pid) {
	   employeeRepository.deleteById(pid); 
   }
   
   public List<Employee> searchByEmployeesdeptName(String dept){
	   return employeeRepository.findEmployeeByNameContainingIgnoreCase(dept); //Invokes method with custom query
   }
}