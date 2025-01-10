package com.dac.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.dac.employee.exception.ResourceNotFoundException;
import com.dac.employee.model.Employee;
import com.dac.employee.service.EmployeeService;


@RestController
@RequestMapping("/api")
public class EmployeeController {

	
	

	@Autowired
	private EmployeeService empService;
	
	@PostMapping("/employees")
	public ResponseEntity<Employee> saveEmployee(@Validated @RequestBody Employee  e) {
				try {
			
					Employee emp1=empService.saveEmployee(e);
		return ResponseEntity.status(HttpStatus.CREATED).body(emp1);
		
		} catch(Exception ex)
		{
			 ex.printStackTrace();
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
					 
		}
	}
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> displayAllEmployee(){
		try {
			List<Employee> emp=empService.listAll();
			return ResponseEntity.ok(emp);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	
	
	
	@GetMapping("/employee/{eid}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="eid") Long eId) 
			throws ResourceNotFoundException{

		Employee e=empService.getSingleEmployee(eId).orElseThrow(() ->
		new  ResourceNotFoundException("employee Not Found for this Id : "+eId)); 

		return ResponseEntity.ok(e);
	}
	
	
	@PutMapping("/employee/{eid}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value="eid") Long eId,
	        @Validated @RequestBody Employee e) throws ResourceNotFoundException {

	    
		Employee employee = empService.getSingleEmployee(eId)
	            .orElseThrow(() -> new ResourceNotFoundException("employee Not Found for this Id : " + eId));

	    
		employee.setDept(e.getDept());
		employee.setFname(e.getFname());
		employee.setLname(e.getLname());
		employee.setSallery(e.getSallery());

	   
	    final Employee updatedemp = empService.saveEmployee(employee); 
	    return ResponseEntity.ok().body(updatedemp);
	}

	
	
			@DeleteMapping("/employee/{eid}")
			public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable(value="eid") Long eId) 
					throws ResourceNotFoundException {

				empService.getSingleEmployee(eId).  
				orElseThrow(() -> new ResourceNotFoundException("Employe Not Found for this Id :"+eId));

				empService.deleteEmployee(eId); 

				Map<String,Boolean> response=new HashMap<>();
				response.put("Deleted", Boolean.TRUE);
				
				return ResponseEntity.ok(response);
			}
				
			
			@GetMapping("/search")
		    public ResponseEntity<?> searchEmployeesdeptName(@RequestParam("dept") String dept) {
		        try {
		            List<Employee> employees = empService.searchByEmployeesdeptName(dept);
		            
		            if (employees.isEmpty()) {
		                return new ResponseEntity<>("No employees found with the given name.", HttpStatus.NOT_FOUND);
		            }
		            
		            return new ResponseEntity<>(employees, HttpStatus.OK);
		        } catch (Exception ex) {
		        	
		            return new ResponseEntity<>("An error occurred while searching for employees.", HttpStatus.INTERNAL_SERVER_ERROR);
		        }
		    }
			
}
