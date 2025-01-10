package com.dac.employee.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dac.employee.model.Employee;



public interface EmployeeRepository  extends JpaRepository<Employee, Long>{

	
	
	public Optional<Employee> findByEid(Long eid);
	
	
	
	
	@Query("SELECT e FROM Employee e WHERE LOWER(e.dept) LIKE LOWER(CONCAT('%', :dept, '%'))")
	List<Employee> findEmployeeByNameContainingIgnoreCase(@Param("dept") String dept);

}