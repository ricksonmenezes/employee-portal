package com.rgarage.employeeportal.employee.domain.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record Contact(


        @NotBlank(message = "Contact is required") String contact,

        @NotBlank(message = "Primary Flag is required") boolean isPrimary) {}
