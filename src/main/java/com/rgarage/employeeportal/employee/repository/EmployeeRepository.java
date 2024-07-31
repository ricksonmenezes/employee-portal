package com.rgarage.employeeportal.employee.repository;

import com.rgarage.employeeportal.employee.domain.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {


}
