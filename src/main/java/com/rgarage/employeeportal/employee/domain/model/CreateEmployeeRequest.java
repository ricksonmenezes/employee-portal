package com.rgarage.employeeportal.employee.domain.model;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

public record CreateEmployeeRequest(
        @Valid @NotEmpty(message = "Items cannot be empty") Set<Contact> contacts,

        /* @Valid @NotEmpty(message = "Items cannot be empty") Set<Address> addresses,*/
        @Valid Employee employee) {}
