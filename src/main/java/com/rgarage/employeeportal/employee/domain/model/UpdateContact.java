package com.rgarage.employeeportal.employee.domain.model;

import javax.validation.constraints.NotBlank;

public record UpdateContact(

        @NotBlank(message = "contact id cannot be empty") Integer id,
        @NotBlank(message = "Contact is required") String contact,
        @NotBlank(message = "Primary Flag is required") boolean isPrimary) {
}
