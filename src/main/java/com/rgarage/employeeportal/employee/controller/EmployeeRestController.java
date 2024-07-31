package com.rgarage.employeeportal.employee.controller;

import com.rgarage.employeeportal.employee.domain.TestEmployeeResponse;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class EmployeeRestController {

    @Autowired
    private  GraphQL graphQL;

    @GetMapping("/employees")
    public TestEmployeeResponse[] getEmployees() {

        TestEmployeeResponse[] employees = new TestEmployeeResponse[3];

        // Create Employee instances and assign them to the array
        employees[0] = new TestEmployeeResponse("E001", "Alice", "Software Engineer");
        employees[1] = new TestEmployeeResponse("E002", "Bob", "Project Manager");
        employees[2] = new TestEmployeeResponse("E003", "Charlie", "QA Tester");
        return employees;

    }

    @PostMapping(value = "/graphql")
    public Map<String, Object> execute(@RequestBody Map<String, String> request, HttpServletRequest raw)
            throws GraphQLException {
        ExecutionResult result = graphQL.execute(request.get("query"));

        return result.getData();
    }
}

