package com.pvb.springboot.veeru.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pvb.springboot.veeru.backend.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("SELECT e FROM Employee e WHERE " +
		       "LOWER(e.firstName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
		       "LOWER(e.lastName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
		       "CAST(e.employeeId AS string) LIKE CONCAT('%', :search, '%')")
		List<Employee> findBySearch(@Param("search") String search);

}
