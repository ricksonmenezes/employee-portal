package com.rgarage.employeeportal.employee.domain.model;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

public record UpdateEmployeeRequest(

        @Valid @NotEmpty(message = "Items cannot be empty") Set<UpdateContact> contacts,

        @Valid @NotEmpty(message = "Items cannot be empty") Set<UpdateAddress> addresses,
        @Valid UpdateEmployee employee) {}
