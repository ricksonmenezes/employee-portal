package com.rgarage.employeeportal.employee.controller;


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


    @PostMapping(value = "/graphql")
    public Map<String, Object> execute(@RequestBody Map<String, String> request, HttpServletRequest raw)
            throws GraphQLException {
        ExecutionResult result = graphQL.execute(request.get("query"));

        return result.getData();
    }
}

