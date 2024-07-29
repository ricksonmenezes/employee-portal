package com.rgarage.employeeportal.employee.controller;

import com.rgarage.employeeportal.employee.domain.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeRestController {

    @GetMapping("/employees")
    public Employee[] getEmployees() {

        Employee[] employees = new Employee[3];

        // Create Employee instances and assign them to the array
        employees[0] = new Employee("E001", "Alice", "Software Engineer");
        employees[1] = new Employee("E002", "Bob", "Project Manager");
        employees[2] = new Employee("E003", "Charlie", "QA Tester");
        return employees;

    }
}
