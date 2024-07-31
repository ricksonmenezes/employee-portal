package com.rgarage.employeeportal.employee.domain.model;


import javax.validation.constraints.NotBlank;

public record Employee(
        @NotBlank(message = "Employee Name is required") String firstName,

        @NotBlank(message = "Employee Name is required") String lastName,
        @NotBlank(message = "Employee middle name is required")  String middleName,
        @NotBlank(message = "Employee Marital Status is required") MaritalStatus maritalStatus,

        @NotBlank(message = "Employee Birth Date is required") long birthDate,

        @NotBlank(message = "Employee Name is required") Gender gender,
        @NotBlank(message = "Employee middle name is required")  String position,
        @NotBlank(message = "Employee Marital Status is required") long hiredDate) {}


