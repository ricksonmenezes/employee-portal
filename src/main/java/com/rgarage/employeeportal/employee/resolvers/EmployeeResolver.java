package com.rgarage.employeeportal.employee.resolvers;

import com.rgarage.employeeportal.employee.domain.model.CreateEmployeeRequest;
import com.rgarage.employeeportal.employee.domain.EmployeeEntity;
import com.rgarage.employeeportal.employee.service.EmployeeService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class EmployeeResolver {

    private final EmployeeService employeeService;

    @GraphQLQuery
    @GraphQLNonNull
    public List<@GraphQLNonNull EmployeeEntity> employees() {

        return this.employeeService.find();
    }

    @GraphQLQuery
    public EmployeeEntity employee(
            @GraphQLArgument(name = "code") Integer code) {
        return this.employeeService.findOneById(code);
    }

    @GraphQLMutation
    public EmployeeEntity createEmployee(
            @GraphQLArgument(name = "input")  CreateEmployeeRequest createEmployee) {
        return this.employeeService.create(createEmployee);
    }

    @GraphQLMutation
    public Integer deleteEmployee(
            @GraphQLArgument(name = "id") Integer code) {
        return this.employeeService.delete(code) ? code : null;
    }

}
