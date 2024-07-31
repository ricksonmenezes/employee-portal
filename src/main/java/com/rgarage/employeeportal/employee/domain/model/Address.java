package com.rgarage.employeeportal.employee.domain.model;

import javax.validation.constraints.NotBlank;

public record Address(

    @NotBlank(message = "Address1  is required") String address1,

    @NotBlank(message = "Address2 is required") String address2,

    @NotBlank(message = "Primary Flag is required") boolean isPrimary) {}

