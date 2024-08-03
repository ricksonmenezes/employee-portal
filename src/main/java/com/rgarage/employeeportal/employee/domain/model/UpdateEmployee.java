package com.rgarage.employeeportal.employee.domain.model;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record UpdateEmployee(

        @NotNull(message = "code is not empty") Integer code,
        @NotBlank(message = "Employee Name is required") String firstName,
        @NotBlank(message = "Employee Name is required") String lastName,
        @NotBlank(message = "Employee middle name is required")  String middleName,
        @NotBlank(message = "Employee Marital Status is required") MaritalStatus maritalStatus,

        @NotBlank(message = "Employee Birth Date is required") long birthDate,

        @NotBlank(message = "Employee Name is required") Gender gender,
        @NotBlank(message = "Employee middle name is required")  String position,
        @NotBlank(message = "Employee Marital Status is required") long hiredDate) {}
